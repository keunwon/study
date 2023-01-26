package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.config.LogSupport
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(
    private val jwtProvider: JwtProvider,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        runCatching {
            resolveToken(request).also { token ->
                jwtProvider.verifyTokenOrThrownError(token)
                registerSecurityContext(token)
            }
            filterChain.doFilter(request, response)
        }.onFailure {
            log.error("> ${it.message}, Request-url: ${request.servletPath}")
            errorResponseBody(response, it)
        }
    }

    private fun registerSecurityContext(token: String) {
        SecurityContextHolder.getContext().authentication = jwtProvider.getAuthentication(token)
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION) ?: throw JwtException("토큰이 비어있거나 존재하지 않습니다.")
        if (!token.startsWith(AUTHORIZATION_TYPE)) throw JwtException("지원하지 않는 토큰 인가 타입입니다.")
        return request.getHeader(HttpHeaders.AUTHORIZATION).substring(AUTHORIZATION_TYPE.length)
    }

    private fun errorResponseBody(response: HttpServletResponse, error: Throwable) {
        val httpStatus = HttpStatus.FORBIDDEN.value()
        val defaultErrorMessage = error.message ?: "토큰 검증 중 알 수 없는 오류가 발생하였습니다."
        val body = ErrorDto(code = httpStatus, message = errorMessages[error::class] ?: defaultErrorMessage)
        response.apply {
            status = httpStatus
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(outputStream, body)
        }
    }

    companion object : LogSupport {
        private const val AUTHORIZATION_TYPE = "Bearer "
        private val errorMessages = mapOf(
            UnsupportedJwtException::class to "토큰 형식이 올바르지 않습니다.",
            MalformedJwtException::class to "토큰이 유효하지 않습니다.",
            SignatureException::class to "시그니처 연산에 실패하였습니다.",
            ExpiredJwtException::class to "유효기간이 만료된 토큰입니다.",
            IllegalArgumentException::class to "토큰이 비어있거나 존재하지 않습니다.",
        )
    }
}

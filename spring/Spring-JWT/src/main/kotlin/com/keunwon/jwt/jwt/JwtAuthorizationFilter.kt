package com.keunwon.jwt.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.config.SecurityConfiguration.Companion.matchSkipRequest
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
    private val jwtProvider: TokenProvider,
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (matchSkipRequest(request)) {
            filterChain.doFilter(request, response)
            return
        }
        process(request, response, filterChain)
    }

    private fun process(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            validateAuthorizationTypeWithThrow(request)
            resolveToken(request).also { token ->
                jwtProvider.verifyTokenWithThrows(token)
                registerSecurityContext(token)
            }
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            log.error("> 토큰 검증 중 오류가 발생하였습니다. Request Url: ${request.servletPath}", e)
            responseBody(response, e)
        }
    }

    private fun registerSecurityContext(token: String) {
        SecurityContextHolder.getContext().authentication = jwtProvider.getAuthentication(token)
    }

    private fun validateAuthorizationTypeWithThrow(request: HttpServletRequest) {
        val headerValue = request.getHeader(HttpHeaders.AUTHORIZATION)
            ?: throw JwtException("토큰이 비어있거나 존재하지 않습니다.")
        if (!headerValue.startsWith(AUTHORIZATION_TYPE)) throw JwtException("지원하지 않는 토큰 인가 타입입니다.")
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION) ?: ""
        return if (token.startsWith(AUTHORIZATION_TYPE)) token.substring(AUTHORIZATION_TYPE.length) else token
    }

    private fun responseBody(response: HttpServletResponse, error: Throwable) {
        val httpStatus = HttpStatus.FORBIDDEN.value()
        val defaultErrorMessage = error.message ?: "토큰 검증 중 알 수 없는 오류가 발생하였습니다."
        val body = ErrorDto(
            code = httpStatus,
            message = errorMessages[error::class] ?: defaultErrorMessage
        )
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

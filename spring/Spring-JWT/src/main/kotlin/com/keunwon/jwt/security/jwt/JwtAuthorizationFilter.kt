package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.config.LogSupport
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
            AuthorizationHeader(request).also {
                jwtProvider.verifyTokenOrThrown(it.credentials)
                registerSecurityContext(it.credentials)
            }
            filterChain.doFilter(request, response)
        }.onFailure { ex ->
            log.error("> 요청 URL: ${request.pathInfo}, 내용: ${ex.message}")
            response.writeErrorResponse(createErrorDto(ex))
        }
    }

    private fun registerSecurityContext(token: String) {
        val claims = jwtProvider.getBody(token)
        val jwtLoginUser = jwtProvider.toJwtLoginUser(claims)
        val roles = jwtLoginUser.roles.map { SimpleGrantedAuthority(it.name) }
        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken.authenticated(jwtLoginUser, "", roles)
    }

    private fun createErrorDto(ex: Throwable): ErrorDto {
        val message = run {
            val defaultMessage = ex.message ?: "토큰 검증을 실패하였습니다"
            JwtProvider.validationErrorMessages[ex::class] ?: defaultMessage
        }
        return ErrorDto(HttpStatus.FORBIDDEN.value(), message)
    }

    private fun HttpServletResponse.writeErrorResponse(errorDto: ErrorDto) = apply {
        status = HttpStatus.FORBIDDEN.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, errorDto)
    }

    companion object : LogSupport
}

class AuthorizationHeader(httpServletRequest: HttpServletRequest) {
    private val authorizationValue = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)

    val type: String
    val credentials: String

    init {
        verifyAuthorizationHeader()
        val (type, credentials) = authorizationValue.split(" ")
        this.type = type
        this.credentials = credentials
        verifyProperties()
    }

    private fun verifyAuthorizationHeader() {
        val value = authorizationValue ?: throw JwtException("Authorization 헤더가 비어있습니다")
        if (value.split(" ").size != 2) throw JwtException("Authorization 헤더 구성이 올바르지 않습니다")
    }

    private fun verifyProperties() {
        if (type != AUTHORIZATION_PREFIX) throw JwtException("지원하지 않는 토큰 타입입니다")
        if (credentials.isBlank()) throw JwtException("토큰이 비어있거나 존재하지 않습니다")
    }

    companion object {
        private const val AUTHORIZATION_PREFIX = "Bearer"
    }
}


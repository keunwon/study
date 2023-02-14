package com.github.keunwon.userauth.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.keunwon.core.generic.ErrorDto
import com.github.keunwon.userauth.jwt.JwtProvider
import io.jsonwebtoken.JwtException
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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
        filterChain: FilterChain
    ) {
        runCatching {
            AuthorizationHeader(request).let {
                jwtProvider.validateToken(it.token)
                registerSecurityContext(it.token)
            }
            filterChain.doFilter(request, response)
        }.onFailure { ex ->
            log.error("> 요청 URL: ${request.pathInfo}, 내용: ${ex.message}")
            response.writeErrorDto(
                ErrorDto(HttpStatus.FORBIDDEN.value(), ex.message)
            )
        }
    }

    private fun registerSecurityContext(token: String) {
        val claims = jwtProvider.getJwsClaims(token).body
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken
            .authenticated(claims, "", emptyList())
    }

    private fun HttpServletResponse.writeErrorDto(errorDto: ErrorDto) = apply {
        status = HttpStatus.FORBIDDEN.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, errorDto)
    }

    companion object {
        private val log = LogFactory.getLog(JwtAuthorizationFilter::class.java)
    }
}

class AuthorizationHeader(request: HttpServletRequest) {
    private val authorizationValue = request.getHeader(HttpHeaders.AUTHORIZATION)

    val type: String
    val token: String

    init {
        verifyAuthorizationHeader()
        val (type, token) = authorizationValue.split(" ")
        this.type = type
        this.token = token
        verifyProperties()
    }

    private fun verifyAuthorizationHeader() {
        val value = authorizationValue ?: throw JwtException("Authorization 헤더가 비어있습니다.")
        if (value.split(" ").size != 2) throw JwtException("Authorization 헤더 구성이 올바르지 않습니다.")
    }

    private fun verifyProperties() {
        if (type != AUTHORIZATION_PREFIX) throw JwtException("지원하지 않는 토큰 타입입니다.")
        if (token.isBlank()) throw JwtException("토큰이 비어있거나 존재하지 않습니다.")
    }

    companion object {
        private const val AUTHORIZATION_PREFIX = "Bearer"
    }
}

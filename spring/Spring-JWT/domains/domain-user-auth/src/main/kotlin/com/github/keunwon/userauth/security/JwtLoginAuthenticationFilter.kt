package com.github.keunwon.userauth.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginAuthenticationFilter(
    loginMatcher: RequestMatcher,
    private val authenticationManager: JwtAuthenticationManager,
    private val objectMapper: ObjectMapper,
) : AbstractAuthenticationProcessingFilter(loginMatcher) {
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val jsonMap = resolveRequest(request)
        val authenticationToken = UsernamePasswordAuthenticationToken
            .unauthenticated(jsonMap[USER_EMAIL], jsonMap[USER_PASSWORD])
        return authenticationManager.authenticate(authenticationToken)
    }

    private fun resolveRequest(request: HttpServletRequest): Map<String, String> {
        if (request.contentType != MediaType.APPLICATION_JSON_VALUE) {
            throw AuthenticationServiceException("지원하지 않는 Content-Type 입니다.")
        }
        val inputStream = StreamUtils.copyToString(request.inputStream, StandardCharsets.UTF_8)
        return objectMapper.readValue<Map<String, String>>(inputStream).also {
            if (it[USER_EMAIL].isNullOrBlank()) {
                throw UsernameNotFoundException("사용자 명이 비어있거나 존재하지 않습니다.")
            }
            if (it[USER_PASSWORD].isNullOrBlank()) {
                throw AuthenticationCredentialsNotFoundException("사용자 비밀번호가 비어있거나 존재하지 않습니다.")
            }
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        successHandler.onAuthenticationSuccess(request, response, authResult)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        failureHandler.onAuthenticationFailure(request, response, failed)
    }

    companion object {
        private const val USER_EMAIL = "email"
        private const val USER_PASSWORD = "password"
    }
}

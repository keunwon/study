package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 사용자 로그인 필터
 * ```
 * POST /auth/login
 * Content-Type: application/json
 * - username: 사용자 아이디
 * - password: 사용자 비밀번호
 * ```
 *
 * @param authenticationManager [JwtAuthenticationManager]
 * @see [JwtLoginAuthenticationSuccessHandler]
 * @see [JwtLoginAuthenticationFailureHandler]
 */
class JwtLoginAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(LOGIN_URL, HttpMethod.POST.name))
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        contentTypeCheck(request)
        val authentication = resolveAuthentication(request)
        return authenticationManager.authenticate(authentication)
    }

    private fun resolveAuthentication(request: HttpServletRequest): Authentication {
        val jsonMap = resolveJsonMap(request).also(this::requiresParams)
        val username = jsonMap[SPRING_SECURITY_FORM_USERNAME_KEY]!!
        val password = jsonMap[SPRING_SECURITY_FORM_PASSWORD_KEY]!!
        return UsernamePasswordAuthenticationToken.unauthenticated(username, password)
    }

    private fun requiresParams(jsonMap: Map<String, String>) {
        if (jsonMap[SPRING_SECURITY_FORM_USERNAME_KEY].isNullOrBlank()) {
            throw UsernameNotFoundException("사용자 명이 비어있거나 존재하지 않습니다.")
        }
        if (jsonMap[SPRING_SECURITY_FORM_PASSWORD_KEY].isNullOrBlank()) {
            throw AuthenticationCredentialsNotFoundException("사용자 비밀번호가 비어있거나 존재하지 않습니다.")
        }
    }

    private fun contentTypeCheck(request: HttpServletRequest) {
        if (request.contentType != MediaType.APPLICATION_JSON_VALUE) {
            throw AuthenticationServiceException("지원하지 않는 contentType 입니다. ${request.contentType}")
        }
    }

    private fun resolveJsonMap(request: HttpServletRequest): Map<String, String> {
        val inputStream = StreamUtils.copyToString(request.inputStream, StandardCharsets.UTF_8)
        return objectMapper.readValue(inputStream, loginBodyTypeRef)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication,
    ) {
        successHandler.onAuthenticationSuccess(request, response, authResult)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException,
    ) {
        super.getFailureHandler().onAuthenticationFailure(request, response, failed)
    }

    companion object {
        const val LOGIN_URL = "/auth/login"
        private val loginBodyTypeRef = jacksonTypeRef<Map<String, String>>()
    }
}

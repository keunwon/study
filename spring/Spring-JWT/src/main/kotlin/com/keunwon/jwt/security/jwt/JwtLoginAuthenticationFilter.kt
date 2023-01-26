package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.User
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginAuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(LOGIN_URL, HttpMethod.POST.name))
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
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

    private fun resolveJsonMap(request: HttpServletRequest): Map<String, String> {
        val inputStream = StreamUtils.copyToString(request.inputStream, StandardCharsets.UTF_8)
        val typeRef = jacksonTypeRef<Map<String, String>>()
        return objectMapper.readValue(inputStream, typeRef)
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
    }
}

open class JwtLoginAuthenticationSuccessHandler(
    private val jwtProvider: JwtProvider,
    private val customUserDetailsService: JwtUserDetailsService<User, Long>,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {

    @Transactional
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val userToken = jwtProvider.createTokenIssue(authentication)
        saveUserToken(authentication, userToken)
        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(this.outputStream, userToken)
        }
    }

    private fun saveUserToken(authentication: Authentication, tokenIssue: TokenIssue) {
        val id = (authentication.principal as User).id
        val user = customUserDetailsService.findLoginUser(id)
            .apply { successLogin(tokenIssue.toEntity(id)) }
        customUserDetailsService.save(user)
    }

    companion object : LogSupport
}

open class JwtLoginAuthenticationFailureHandler(
    private val objectMapper: ObjectMapper,
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        response.apply {
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(this.outputStream, createBody(exception.message))
        }
    }

    private fun createBody(errorMessage: String?) = ErrorDto(
        code = HttpStatus.UNAUTHORIZED.value(),
        message = errorMessage ?: "로그인을 실패하였습니다."
    )
}

package com.keunwon.jwt.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.config.CustomUserDetailsServiceImpl
import com.keunwon.jwt.config.LogSupport
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
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
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(authenticationManager: AuthenticationManager)
    : UsernamePasswordAuthenticationFilter(authenticationManager) {

    init {
        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher(LOGIN_URL, HttpMethod.POST.name))
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authentication = resolveAuthentication(request)
        return authenticationManager.authenticate(authentication)
    }

    /*override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        try {
            super.doFilter(request, response, chain)
        } catch (e: Exception) {
            val errorDto = ErrorDto(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "서버에 문제가 발생하여 로그인이 불가능합니다."
            )
            (response as HttpServletResponse).apply {
                status = HttpStatus.INTERNAL_SERVER_ERROR.value()
                contentType = MediaType.APPLICATION_JSON_VALUE
                objectMapper.writeValue(outputStream, errorDto)
            }
        }
    }*/

    private fun resolveAuthentication(request: HttpServletRequest): Authentication {
        val jsonMap = resolveJsonMap(request)
        val username = jsonMap[SPRING_SECURITY_FORM_USERNAME_KEY] ?: ""
        val password = jsonMap[SPRING_SECURITY_FORM_PASSWORD_KEY] ?: ""
        return UsernamePasswordAuthenticationToken
            .unauthenticated(username, password)
            .also(this::validationWithThrow)
    }

    private fun validationWithThrow(authentication: Authentication) {
        val username = authentication.principal as String
        val password = authentication.credentials as String
        when {
            username.isBlank() -> throw UsernameNotFoundException("사용자 명이 비어있거나 존재하지 않습니다.")
            password.isBlank() -> throw AuthenticationCredentialsNotFoundException("사용자 비밀번호가 비어있거나 존재하지 않습니다.")
        }
    }

    private fun resolveJsonMap(request: HttpServletRequest): Map<String, String> {
        val inputStream = StreamUtils.copyToString(request.inputStream, StandardCharsets.UTF_8)
        val typeRef = jacksonTypeRef<Map<String, String>>()
        return jacksonObjectMapper().readValue(inputStream, typeRef)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        successHandler.onAuthenticationSuccess(request, response, authResult)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        super.getFailureHandler().onAuthenticationFailure(request, response, failed)
    }

    companion object {
        const val LOGIN_URL = "/auth/login"
        val objectMapper = jacksonObjectMapper()
    }
}

data class TokenIssue(
    val accessToken: String,
    val refreshToken: String,
)

open class CustomAuthenticationSuccessHandler(
    private val tokenProvider: TokenProvider,
    private val customUserDetailsService: CustomUserDetailsServiceImpl,
) : AuthenticationSuccessHandler {

    @Transactional
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val id = JwtAuthenticationManager.userContext.get().id as Long
        customUserDetailsService.successLogin(id)
        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            jacksonObjectMapper().writeValue(this.outputStream, createBody(authentication))
        }
    }

    private fun createBody(authentication: Authentication) = TokenIssue(
        accessToken = tokenProvider.generateToken(authentication),
        refreshToken = tokenProvider.generateToken(authentication, Timestamp.valueOf(LocalDateTime.now().plusMonths(1L)))
    )

    companion object : LogSupport
}

open class CustomAuthenticationFailureHandler(
    private val customUserDetailsService: CustomUserDetailsServiceImpl,
) : AuthenticationFailureHandler {

    @Transactional
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        if (exception is BadCredentialsException) {
            val id = JwtAuthenticationManager.userContext.get().id as Long
            customUserDetailsService.incFailCount(id)
        }
        response.apply {
            val errorBody = createBody(exception.message)
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            jacksonObjectMapper().writeValue(this.outputStream, errorBody)
        }
    }

    private fun createBody(errorMessage: String?) = ErrorDto(
        code = HttpStatus.UNAUTHORIZED.value(),
        message = errorMessage ?: "로그인을 실패하였습니다."
    )
}

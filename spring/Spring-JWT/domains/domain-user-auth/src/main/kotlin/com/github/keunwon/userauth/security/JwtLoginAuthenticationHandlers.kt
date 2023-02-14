package com.github.keunwon.userauth.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.keunwon.core.generic.ErrorDto
import com.github.keunwon.userauth.jwt.LoginToken
import com.github.keunwon.userauth.usertoken.UserTokenService
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginAuthenticationSuccessHandler(
    private val userTokenService: UserTokenService,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val loginToken = userTokenService.issueLoginToken(authentication.name)
        response.writeLoginToken(loginToken)
        loggingLogin(authentication, loginToken)
    }

    private fun HttpServletResponse.writeLoginToken(loginToken: LoginToken) = apply {
        status = HttpStatus.OK.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, loginToken)
    }

    private fun loggingLogin(authentication: Authentication, loginToken: LoginToken) {
        log.info("=== [s] 사용자 토큰 발급 성공 ====")
        log.info("> 사용자 명: ${authentication.name}")
        log.info("> accessToken 만료 일자: ${loginToken.accessToken.expirationDateTime()}")
        log.info("> refreshToken 만료 일자: ${loginToken.refreshToken.expirationDateTime()}")
        log.info("=== [e] 사용자 토큰 발급 성공 ====")
    }

    companion object {
        private val log = LogFactory.getLog(JwtLoginAuthenticationSuccessHandler::class.java)
    }
}

class JwtLoginAuthenticationFailureHandler(
    private val objectMapper: ObjectMapper,
) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        response.writeErrorDto(
            ErrorDto(HttpStatus.UNAUTHORIZED.value(), exception.message)
        )
    }

    private fun HttpServletResponse.writeErrorDto(errorDto: ErrorDto) = apply {
        status = HttpStatus.UNAUTHORIZED.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, errorDto)
    }
}

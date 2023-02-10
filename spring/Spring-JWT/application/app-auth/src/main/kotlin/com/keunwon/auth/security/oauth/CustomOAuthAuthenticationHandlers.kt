package com.keunwon.auth.security.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.auth.common.ErrorDto
import com.keunwon.auth.config.LogSupport
import com.keunwon.auth.domain.user.UserRepository
import com.keunwon.auth.domain.user.UserToken
import com.keunwon.auth.domain.user.UserTokenRepository
import com.keunwon.auth.domain.user.getByEmail
import com.keunwon.auth.security.jwt.ClaimsInfo
import com.keunwon.auth.security.jwt.JwtProvider
import com.keunwon.auth.security.jwt.JwtRefreshToken
import com.keunwon.auth.security.jwt.LoginTokenResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class OAuthAuthenticationSuccessHandler(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val user = userRepository.getByEmail(authentication.name)
        val loginToken = jwtProvider.generateLoginSuccessToken(ClaimsInfo(user))
        saveOrUpdateRefreshToken(user.id, loginToken.refreshToken)
        response.writeLoginToken(LoginTokenResponse(loginToken))
    }

    private fun saveOrUpdateRefreshToken(userId: Long, refreshToken: JwtRefreshToken) {
        val userToken = userTokenRepository.findByUserId(userId)?.apply {
            updateRefreshToken(refreshToken)
        } ?: UserToken(userId, refreshToken)
        userTokenRepository.save(userToken)
    }

    private fun HttpServletResponse.writeLoginToken(body: LoginTokenResponse) = apply {
        status = HttpStatus.OK.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, body)
    }
}

class OAuthAuthenticationFailureHandler(
    private val objectMapper: ObjectMapper,
) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        log.error("> OAuth 로그인 실패", exception)
        response.apply {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(outputStream, errorDto)
        }
    }

    companion object : LogSupport {
        private val errorDto = ErrorDto(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "로그인을 실패하였습니다.",
        )
    }
}

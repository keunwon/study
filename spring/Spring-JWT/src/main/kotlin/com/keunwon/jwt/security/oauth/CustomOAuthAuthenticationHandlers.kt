package com.keunwon.jwt.security.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserToken
import com.keunwon.jwt.domain.UserTokenRepository
import com.keunwon.jwt.domain.getByUsername
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import com.keunwon.jwt.security.jwt.JwtProvider
import com.keunwon.jwt.security.jwt.JwtRefreshToken
import com.keunwon.jwt.security.jwt.LoginTokenResponse
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
        val loginToken = jwtProvider.generateLoginSuccessToken(CreateTokenRequest.from(authentication))
        saveOrUpdateRefreshToken(authentication, loginToken.refreshToken)
        response.writeLoginToken(LoginTokenResponse(loginToken))
    }

    private fun HttpServletResponse.writeLoginToken(body: LoginTokenResponse) = apply {
        status = HttpStatus.OK.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, body)
    }

    private fun saveOrUpdateRefreshToken(authentication: Authentication, refreshToken: JwtRefreshToken) {
        val user = userRepository.getByUsername(authentication.name)
        val userToken = userTokenRepository.findByUserId(user.id)?.apply { updateRefreshToken(refreshToken) }
            ?: UserToken(user.id, refreshToken)
        userTokenRepository.save(userToken)
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
        response.apply {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(outputStream, errorDto)
        }
    }

    companion object {
        private val errorDto = ErrorDto(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "로그인을 실패하였습니다.",
        )
    }
}

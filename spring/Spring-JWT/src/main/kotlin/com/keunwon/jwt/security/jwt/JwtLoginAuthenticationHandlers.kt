package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.domain.UserToken
import com.keunwon.jwt.domain.UserTokenRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtLoginAuthenticationSuccessHandler(
    private val jwtProvider: JwtProvider,
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
        response.writeLoginToken(LoginTokenResponse.from(loginToken))
    }

    private fun HttpServletResponse.writeLoginToken(loginTokenResponse: LoginTokenResponse) = apply {
        status = HttpStatus.OK.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, loginTokenResponse)
    }

    private fun saveOrUpdateRefreshToken(authentication: Authentication, refreshToken: JwtRefreshToken) {
        val userId = authentication.name.toLong()
        userTokenRepository.save(loadUserToken(userId, refreshToken))
    }

    private fun loadUserToken(userId: Long, refreshToken: JwtRefreshToken): UserToken {
        val tokenValue = refreshToken.value
        val expiresAt = refreshToken.expiredAt.toLocalDateTime()
        return userTokenRepository.findByUserId(userId)
            ?.apply { updateRefreshToken(tokenValue, expiresAt) }
            ?: UserToken(userId, tokenValue, expiresAt)
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
        response.apply {
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(this.outputStream, errorBody(exception.message))
        }
    }

    private fun errorBody(errorMessage: String?) = ErrorDto(
        code = HttpStatus.UNAUTHORIZED.value(),
        message = errorMessage ?: "로그인을 실패하였습니다."
    )
}

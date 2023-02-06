package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserToken
import com.keunwon.jwt.domain.user.UserTokenRepository
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
        val user = authentication.principal as User
        generateJwtLoginToken(user).also { loginToken ->
            saveOrUpdateRefreshToken(user, loginToken.refreshToken)
            response.writeLoginToken(LoginTokenResponse(loginToken))
            loggingLogin(loginToken)
        }
    }

    private fun generateJwtLoginToken(user: User): JwtLoginToken {
        val createTokenRequest = CreateToken(user.username!!, listOf(user.role.name))
        return jwtProvider.generateLoginSuccessToken(createTokenRequest)
    }

    private fun HttpServletResponse.writeLoginToken(loginTokenResponse: LoginTokenResponse) = apply {
        status = HttpStatus.OK.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, loginTokenResponse)
    }

    private fun saveOrUpdateRefreshToken(user: User, refreshToken: JwtRefreshToken) {
        val userToken = userTokenRepository.findByUserId(user.id)?.apply { updateRefreshToken(refreshToken) }
            ?: UserToken(user.id, refreshToken)
        userTokenRepository.save(userToken)
    }

    private fun loggingLogin(jwtLoginToken: JwtLoginToken) {
        val claims = jwtProvider.getBody(jwtLoginToken.accessToken.value)
        log.info("===== [s] 사용자 토큰 발급 성공 =====")
        log.info("> 사용자 명: ${claims.subject}")
        log.info("> accessToken 만료 일자: ${claims.expiration.toLocalDateTime()}")
        log.info("> refreshToken 만료 일자: ${claims.issuedAt.toLocalDateTime()}")
        log.info("===== [e] 사용자 토큰 발급 성공 =====")
    }

    companion object : LogSupport
}

class JwtLoginAuthenticationFailureHandler(
    private val objectMapper: ObjectMapper,
) : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException,
    ) {
        val errorDto = createErrorDto(exception.message)
        response.writeErrorBody(errorDto)
    }

    private fun HttpServletResponse.writeErrorBody(errorDto: ErrorDto) = apply {
        status = HttpStatus.UNAUTHORIZED.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, errorDto)
    }

    private fun createErrorDto(errorMessage: String?) = ErrorDto(
        code = HttpStatus.UNAUTHORIZED.value(),
        message = errorMessage ?: "로그인을 실패하였습니다."
    )

    companion object : LogSupport
}

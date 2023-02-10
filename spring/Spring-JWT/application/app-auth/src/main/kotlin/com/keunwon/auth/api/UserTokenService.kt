package com.keunwon.auth.api

import com.keunwon.auth.domain.user.UserRepository
import com.keunwon.auth.domain.user.UserTokenRepository
import com.keunwon.auth.domain.user.getByEmail
import com.keunwon.auth.domain.user.getByUserId
import com.keunwon.auth.security.jwt.JwtProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserTokenService(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
    private val jwtProvider: JwtProvider,
) {
    @Transactional
    fun refreshAccessToken(accessTokenIssueRequest: AccessTokenIssueRequest): AccessToken {
        validateRefreshToken(accessTokenIssueRequest)
        return jwtProvider.generateAccessTokenBy(accessTokenIssueRequest.refreshToken).let(::AccessToken)
    }

    private fun validateRefreshToken(accessTokenIssueRequest: AccessTokenIssueRequest) = with(accessTokenIssueRequest) {
        jwtProvider.verifyTokenOrThrown(accessTokenIssueRequest.refreshToken)
        val user = userRepository.getByEmail(accessTokenIssueRequest.email)
        val userToken = userTokenRepository.getByUserId(user.id)
        require(refreshToken == userToken.refreshToken) { "refreshToken 일치하지 않습니다" }
    }
}

package com.keunwon.jwt.api

import com.keunwon.jwt.domain.user.UserRepository
import com.keunwon.jwt.domain.user.UserTokenRepository
import com.keunwon.jwt.domain.user.getByUserId
import com.keunwon.jwt.domain.user.getByUsername
import com.keunwon.jwt.security.jwt.JwtProvider
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
        return jwtProvider.generateAccessTokenWith(accessTokenIssueRequest.refreshToken).let(::AccessToken)
    }

    private fun validateRefreshToken(accessTokenIssueRequest: AccessTokenIssueRequest) = with(accessTokenIssueRequest) {
        jwtProvider.verifyTokenOrThrown(accessTokenIssueRequest.refreshToken)
        val user = userRepository.getByUsername(accessTokenIssueRequest.username)
        val userToken = userTokenRepository.getByUserId(user.id)
        require(refreshToken == userToken.refreshToken) { "refreshToken 일치하지 않습니다" }
    }
}

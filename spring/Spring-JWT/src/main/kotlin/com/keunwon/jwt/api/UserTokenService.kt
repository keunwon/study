package com.keunwon.jwt.api

import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserTokenRepository
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
    fun refreshAccessToken(accessTokenIssue: AccessTokenIssue): AccessToken {
        validateRefreshToken(accessTokenIssue)
        return jwtProvider.generateAccessTokenWith(accessTokenIssue.refreshToken)
            .run { AccessToken(value, expiredAt.toLocalDateTime()) }
    }

    private fun validateRefreshToken(accessTokenIssue: AccessTokenIssue) {
        jwtProvider.verifyTokenOrThrownError(accessTokenIssue.refreshToken)
        val user = userRepository.findByUsername(accessTokenIssue.username)
            ?: throw IllegalArgumentException("사용자가 존재하지 않습니다.")
        val userToken = userTokenRepository.findByUserId(user.id)
            ?: throw IllegalArgumentException("토큰 정보가 존재하지 않습니다.")
        require(accessTokenIssue.refreshToken == userToken.refreshToken) { "refreshToken 일치하지 않습니다" }
    }
}

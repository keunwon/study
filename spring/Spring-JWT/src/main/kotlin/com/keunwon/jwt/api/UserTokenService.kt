package com.keunwon.jwt.api

import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserTokenRepository
import com.keunwon.jwt.domain.getByUserId
import com.keunwon.jwt.domain.getByUsername
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
        return jwtProvider.generateAccessTokenWith(accessTokenIssue.refreshToken).let(::AccessToken)
    }

    private fun validateRefreshToken(accessTokenIssue: AccessTokenIssue) = with(accessTokenIssue) {
        val user = userRepository.getByUsername(accessTokenIssue.username)
        val userToken = userTokenRepository.getByUserId(user.id)
        require(refreshToken == userToken.refreshToken) { "refreshToken 일치하지 않습니다." }
    }
}

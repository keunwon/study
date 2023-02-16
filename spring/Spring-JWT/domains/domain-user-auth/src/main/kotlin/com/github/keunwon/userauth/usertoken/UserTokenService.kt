package com.github.keunwon.userauth.usertoken

import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.memeber.getByEmail
import com.github.keunwon.userauth.jwt.AccessToken
import com.github.keunwon.userauth.jwt.AccessTokenClaims
import com.github.keunwon.userauth.jwt.JwtProvider
import com.github.keunwon.userauth.jwt.LoginToken
import com.github.keunwon.userauth.jwt.RefreshTokenClaims
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserTokenService(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
) {
    fun issueLoginToken(email: String): LoginToken {
        val user = userRepository.getByEmail(email)
        return jwtProvider.createLoginToken(
            AccessTokenClaims(user),
            RefreshTokenClaims(user.profile.email),
        ).also { token ->
            userTokenRepository.findByUserId(user.id)?.apply {
                return@also update(token.refreshToken)
            }
            userTokenRepository.save(UserToken(user.id, token.refreshToken))
        }
    }

    fun reissueAccessToken(reissue: Reissue): AccessToken {
        jwtProvider.validateToken(reissue.token)
        val user = userRepository.getByEmail(reissue.email)
        val userToken = userTokenRepository.getByUserId(user.id)
        require(reissue.token == userToken.refreshToken) { "refreshToken 일치하지 않습니다." }
        return jwtProvider.createAccessToken(AccessTokenClaims(user))
    }
}

data class Reissue(
    val email: String,
    val token: String,
)

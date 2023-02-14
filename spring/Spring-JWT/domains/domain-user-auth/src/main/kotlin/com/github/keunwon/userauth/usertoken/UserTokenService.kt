package com.github.keunwon.userauth.usertoken

import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.memeber.getByEmail
import com.github.keunwon.userauth.jwt.AccessToken
import com.github.keunwon.userauth.jwt.JwtClaims
import com.github.keunwon.userauth.jwt.JwtProvider
import com.github.keunwon.userauth.jwt.LoginToken
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
        return jwtProvider.createLoginToken(JwtClaims.loginToken(user)).also { token ->
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
        return jwtProvider.createAccessToken(user.toAccessJwtClaims())
    }

    private fun User.toAccessJwtClaims(): JwtClaims = JwtClaims.accessToken(profile.email, id, role)
}

data class Reissue(
    val email: String,
    val token: String,
)

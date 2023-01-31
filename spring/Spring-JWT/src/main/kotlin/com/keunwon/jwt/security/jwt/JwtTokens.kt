package com.keunwon.jwt.security.jwt

import java.time.Instant

data class JwtLoginToken(
    val accessToken: JwtAccessToken,
    val refreshToken: JwtRefreshToken,
)

abstract class AbstractJwtToken {
    abstract val value: String
    abstract val issuedAt: Instant
    abstract val expiredAt: Instant
}

data class JwtAccessToken(
    override val value: String,
    override val issuedAt: Instant,
    override val expiredAt: Instant,
) : AbstractJwtToken()

data class JwtRefreshToken(
    override val value: String,
    override val issuedAt: Instant,
    override val expiredAt: Instant,
) : AbstractJwtToken()

/**
 * 로그인 성공 응답
 */
data class LoginTokenResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun from(loginToken: JwtLoginToken) = LoginTokenResponse(
            accessToken = loginToken.accessToken.value,
            refreshToken = loginToken.refreshToken.value,
        )
    }
}

package com.keunwon.auth.security.jwt

import io.jsonwebtoken.Claims
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
) : AbstractJwtToken() {
    constructor(token: String, claims: Claims) : this(
        token,
        claims.issuedAt.toInstant(),
        claims.expiration.toInstant()
    )
}

data class JwtRefreshToken(
    override val value: String,
    override val issuedAt: Instant,
    override val expiredAt: Instant,
) : AbstractJwtToken() {
    constructor(token: String, claims: Claims) : this(
        token,
        claims.issuedAt.toInstant(),
        claims.expiration.toInstant(),
    )
}

/**
 * 로그인 성공 응답
 */
data class LoginTokenResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    constructor(jwtLoginToken: JwtLoginToken) : this(
        jwtLoginToken.accessToken.value,
        jwtLoginToken.refreshToken.value,
    )
}

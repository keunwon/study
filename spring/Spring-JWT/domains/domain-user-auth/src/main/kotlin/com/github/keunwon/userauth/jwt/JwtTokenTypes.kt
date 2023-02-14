package com.github.keunwon.userauth.jwt

import com.github.keunwon.core.toLocalDateTime
import java.time.LocalDateTime
import java.util.*

sealed class AbstractToken {
    abstract val value: String
    abstract val expiration: Date

    fun expirationDateTime(): LocalDateTime = expiration.toLocalDateTime()
}

class AccessToken(
    override val value: String,
    override val expiration: Date,
) : AbstractToken()

data class RefreshToken(
    override val value: String,
    override val expiration: Date,
) : AbstractToken()

data class LoginToken(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken,
)

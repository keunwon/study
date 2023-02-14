package com.github.keunwon.userauth.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val secret: String,
    val issuer: String,
    val accessTokenValiditySeconds: Long,
    val refreshTokenValiditySeconds: Long,
) {
    val expirationAccessToken: Date
        get() = Date(Date().time + accessTokenValiditySeconds * 1000)

    val expirationRefreshToken: Date
        get() = Date(Date().time + refreshTokenValiditySeconds * 1000)
}

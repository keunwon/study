package com.keunwon.jwt

import com.keunwon.jwt.security.jwt.AbstractJwtToken
import com.keunwon.jwt.security.jwt.CreateToken
import com.keunwon.jwt.security.jwt.JwtProperties
import com.keunwon.jwt.security.jwt.JwtProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

val testTokenProvider: JwtProvider = run {
    val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    val jwtProperties = JwtProperties(
        secret = Encoders.BASE64.encode(key.encoded),
        accessTokenValiditySeconds = 60,
        refreshTokenValiditySeconds = 60,
    )
    JwtProvider("test-app", jwtProperties)
}

val unExpiredDate: Date = run {
    val dateTime = LocalDateTime.of(2030, 12, 31, 0, 0)
    Date.from(dateTime.toInstant(ZoneOffset.UTC))
}

val expiredDate: Date = run {
    val dateTime = LocalDateTime.of(2022, 12, 31, 0, 0)
    Date.from(dateTime.toInstant(ZoneOffset.UTC))
}

fun createToken(
    expired: Boolean = false,
    username: String = LOGIN_USERNAME,
    roles: List<String> = AUTHENTICATION_ROLES.map { it.authority },
    claims: Map<String, Any> = emptyMap(),
): AbstractJwtToken {
    val expirationDate = if (expired) expiredDate else unExpiredDate
    val createTokenRequest = CreateToken(username, roles, claims)
    val token = testTokenProvider.generateToken(createTokenRequest, expirationDate)
    return object : AbstractJwtToken() {
        override val value: String = token
        override val issuedAt: Instant = Date().toInstant()
        override val expiredAt: Instant = expiredDate.toInstant()
    }
}

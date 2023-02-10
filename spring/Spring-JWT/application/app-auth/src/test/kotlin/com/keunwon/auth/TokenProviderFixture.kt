package com.keunwon.auth

import com.keunwon.auth.domain.USER_EMAIL
import com.keunwon.auth.security.jwt.AbstractJwtToken
import com.keunwon.auth.security.jwt.ClaimsInfo
import com.keunwon.auth.security.jwt.JwtProperties
import com.keunwon.auth.security.jwt.JwtProvider
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
    userId: Long = 0L,
    email: String = USER_EMAIL,
    expired: Boolean = false,
    roles: List<String> = AUTHENTICATION_ROLES.map { it.authority },
): AbstractJwtToken {
    val expirationDate = if (expired) expiredDate else unExpiredDate
    val createTokenRequest = createClaimsInfo(userId, email, roles)
    val token = testTokenProvider.generateToken(createTokenRequest, expirationDate)
    return object : AbstractJwtToken() {
        override val value: String = token
        override val issuedAt: Instant = Date().toInstant()
        override val expiredAt: Instant = expiredDate.toInstant()
    }
}

fun createClaimsInfo(
    userId: Long = 0,
    subject: String = USER_EMAIL,
    roles: List<String> = AUTHENTICATION_ROLES.map { it.authority },
): ClaimsInfo {
    return ClaimsInfo(userId, subject, roles)
}

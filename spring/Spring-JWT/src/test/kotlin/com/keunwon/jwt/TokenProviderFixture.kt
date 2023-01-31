package com.keunwon.jwt

import com.keunwon.jwt.security.jwt.JwtProperties
import com.keunwon.jwt.security.jwt.JwtProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object TokenProviderFixture {
    private val properties = JwtProperties(
        secret = testKey(),
        accessTokenValiditySeconds = 60,
        refreshTokenValiditySeconds = 60,
    )

    val testTokenProvider = JwtProvider("test-app", properties)

    val unExpiredDate: Date = run {
        val dateTime = LocalDateTime.of(2030, 12, 31, 0, 0)
        Date.from(dateTime.toInstant(ZoneOffset.UTC))
    }

    val expiredDate: Date = run {
        val dateTime = LocalDateTime.of(2022, 12, 31, 0, 0)
        Date.from(dateTime.toInstant(ZoneOffset.UTC))
    }

    private fun testKey(algorithm: SignatureAlgorithm = SignatureAlgorithm.HS512): String {
        val key = Keys.secretKeyFor(algorithm)
        return Encoders.BASE64.encode(key.encoded)
    }
}

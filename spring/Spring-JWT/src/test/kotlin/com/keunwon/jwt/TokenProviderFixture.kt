package com.keunwon.jwt

import com.keunwon.jwt.security.jwt.AbstractJwtToken
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import com.keunwon.jwt.security.jwt.JwtProperties
import com.keunwon.jwt.security.jwt.JwtProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object JwtAuthenticationManagerStub : AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        return createPostAuthenticationToken()
    }
}

object TokenProviderFixture {
    val testTokenProvider = run {
        val properties = JwtProperties(
            secret = testKey(),
            accessTokenValiditySeconds = 60,
            refreshTokenValiditySeconds = 60,
        )
        JwtProvider("test-app", properties)
    }

    fun createToken(expired: Boolean, username: String, roles: List<String>): AbstractJwtToken {
        val expirationDate = if (expired) expiredDate else unExpiredDate
        val token = testTokenProvider.generateToken(CreateTokenRequest(username, roles), expirationDate)
        return object : AbstractJwtToken() {
            override val value: String = token
            override val issuedAt: Instant = Date().toInstant()
            override val expiredAt: Instant = expiredDate.toInstant()
        }
    }

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

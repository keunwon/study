package com.keunwon.jwt

import com.keunwon.jwt.security.jwt.JwtProperties
import com.keunwon.jwt.security.jwt.JwtProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys

object TokenProviderFixture {
    val testTokenProvider = JwtProvider("testApp", testJwtProperties())

    init {
        testTokenProvider.afterPropertiesSet()
    }

    private fun testJwtProperties(): JwtProperties = JwtProperties(
        secret = testKey(),
        accessTokenValiditySeconds = 60,
        refreshTokenValiditySeconds = 60
    )

    private fun testKey(algorithm: SignatureAlgorithm = SignatureAlgorithm.HS512): String {
        val key = Keys.secretKeyFor(algorithm)
        return Encoders.BASE64.encode(key.encoded)
    }
}

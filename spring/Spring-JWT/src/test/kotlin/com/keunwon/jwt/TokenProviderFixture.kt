package com.keunwon.jwt

import com.keunwon.jwt.jwt.TokenProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys

class TokenProviderFixture {
    companion object {
        val testTokenProvider = TokenProvider("testApp", createKey(), 300)

        init {
            testTokenProvider.afterPropertiesSet()
        }

        fun createKey(algorithm: SignatureAlgorithm = SignatureAlgorithm.HS512): String {
            val key = Keys.secretKeyFor(algorithm)
            return Encoders.BASE64.encode(key.encoded)
        }
    }
}

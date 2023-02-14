package com.github.keunwon.userauth

import com.github.keunwon.core.toDate
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.userauth.jwt.JwtClaims
import com.github.keunwon.userauth.jwt.JwtProperties
import com.github.keunwon.userauth.jwt.JwtProvider
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import java.time.LocalDateTime

val jwtPropertiesFixture = run {
    val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    JwtProperties(
        secret = Encoders.BASE64.encode(key.encoded),
        issuer = "test-app",
        accessTokenValiditySeconds = 60,
        refreshTokenValiditySeconds = 120,
    )
}

val jwtProviderFixture = JwtProvider(jwtPropertiesFixture)

val globalLoginToken = jwtProviderFixture.createLoginToken(
    JwtClaims.loginToken(UserBuilder().build())
)

val globalExpiredToken = run {
    val date = LocalDateTime.of(2023, 1, 1, 0, 0).toDate()
    jwtProviderFixture.createTokenString(emptyMap(), date)
}

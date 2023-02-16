package com.github.keunwon.userauth

import com.github.keunwon.core.toDate
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.userauth.jwt.AccessTokenClaims
import com.github.keunwon.userauth.jwt.JwtProperties
import com.github.keunwon.userauth.jwt.JwtProvider
import com.github.keunwon.userauth.jwt.RefreshTokenClaims
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

val globalLoginToken = UserBuilder().build().run {
    jwtProviderFixture.createLoginToken(
        AccessTokenClaims(this),
        RefreshTokenClaims(profile.email)
    )
}

val globalExpiredToken = run {
    val date = LocalDateTime.of(2023, 1, 1, 0, 0).toDate()
    jwtProviderFixture.createTokenString(emptyMap(), date)
}

package com.github.keunwon.userauth.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.keunwon.core.enums.UserRole
import com.github.keunwon.user.memeber.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
@EnableConfigurationProperties(JwtProperties::class)
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper = jacksonObjectMapper(),
) {
    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))

    fun createLoginToken(
        accessTokenClaims: AccessTokenClaims,
        refreshTokenClaims: RefreshTokenClaims,
    ): LoginToken {
        return LoginToken(createAccessToken(accessTokenClaims), createRefreshToken(refreshTokenClaims))
    }

    fun createAccessToken(claims: AccessTokenClaims): AccessToken {
        val token = createTokenString(toMap(claims), jwtProperties.expirationAccessToken)
        return AccessToken(token, jwtProperties.expirationAccessToken)
    }

    fun createRefreshToken(claims: RefreshTokenClaims): RefreshToken {
        val token = createTokenString(toMap(claims), jwtProperties.expirationRefreshToken)
        return RefreshToken(token, jwtProperties.expirationRefreshToken)
    }

    fun createTokenString(claims: Map<String, Any>, expiration: Date): String {
        return Jwts.builder()
            .setIssuer(jwtProperties.issuer)
            .addClaims(claims)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiration)
            .compact()
    }

    fun validateToken(token: String?) = getJwsClaims(token)

    fun tokenConvertLoginUserDto(accessToken: String): LoginUserDto {
        val claims = getJwsClaims(accessToken).body
        return LoginUserDto(
            email = claims.subject,
            nickname = claims["nickname"].toString(),
            role = UserRole.valueOf(claims["role"].toString()),
        )
    }

    fun getJwsClaims(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
    }

    private fun <T : Any> toMap(value: T): Map<String, Any> = objectMapper.convertValue(value)
}

data class AccessTokenClaims(
    val sub: String,
    val id: Long,
    val nickname: String,
    val role: UserRole,
) {
    constructor(user: User) : this(
        user.profile.email,
        user.id,
        user.profile.nickname,
        user.role,
    )
}

data class RefreshTokenClaims(
    val sub: String,
)

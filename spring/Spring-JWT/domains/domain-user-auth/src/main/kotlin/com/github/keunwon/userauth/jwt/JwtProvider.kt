package com.github.keunwon.userauth.jwt

import com.fasterxml.jackson.annotation.JsonInclude
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
class JwtProvider(private val jwtProperties: JwtProperties) {
    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))

    private val objectMapper = jacksonObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    fun createLoginToken(claims: JwtClaims): LoginToken {
        return LoginToken(createAccessToken(claims), createRefreshToken(claims))
    }

    fun createAccessToken(claims: JwtClaims): AccessToken {
        val token = createTokenString(toMap(claims), jwtProperties.expirationAccessToken)
        return AccessToken(token, jwtProperties.expirationAccessToken)
    }

    fun createRefreshToken(claims: JwtClaims): RefreshToken {
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

    fun getJwsClaims(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
    }

    private fun <T : Any> toMap(value: T): Map<String, Any> = objectMapper.convertValue(value)
}

class JwtClaims private constructor(
    val sub: String,
    val id: Long? = null,
    val role: UserRole? = null,
) {
    companion object {
        fun loginToken(user: User) = JwtClaims(user.profile.email, user.id, user.role)
        fun accessToken(sub: String, id: Long, role: UserRole) = JwtClaims(sub, id, role)
        fun refreshToken(sub: String) = JwtClaims(sub)
    }
}

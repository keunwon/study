package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.config.LogSupport
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
@EnableConfigurationProperties(JwtProperties::class)
class JwtProvider(
    @Value("\${spring.application.name}") private val appName: String,
    private val jwtProperties: JwtProperties,
) {
    private var key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))

    fun generateLoginSuccessToken(tokenRequest: CreateTokenRequest): JwtLoginToken {
        return JwtLoginToken(generateAccessToken(tokenRequest), generateRefreshToken(tokenRequest))
    }

    fun generateAccessToken(tokenRequest: CreateTokenRequest): JwtAccessToken {
        val tokenValue = generateToken(tokenRequest, jwtProperties.expiredDateByAccessToken)
        val claims = getBody(tokenValue)
        return JwtAccessToken(tokenValue, claims.issuedAt.toInstant(), claims.expiration.toInstant())
    }

    fun generateRefreshToken(tokenRequest: CreateTokenRequest): JwtRefreshToken {
        val tokenValue = generateToken(tokenRequest, jwtProperties.expirationDateByRefreshToken)
        val claims = getBody(tokenValue)
        return JwtRefreshToken(tokenValue, claims.issuedAt.toInstant(), claims.expiration.toInstant())
    }

    fun generateAccessTokenWith(refreshToken: String): JwtAccessToken {
        val claims = getBody(refreshToken)
        return generateAccessToken(CreateTokenRequest(claims.subject, getRoles(claims)))
    }

    fun generateToken(tokenRequest: CreateTokenRequest, expiredDate: Date): String {
        return Jwts.builder()
            .setIssuer(appName)
            .setSubject(tokenRequest.subject)
            .claim(ROLE_KEY, tokenRequest.roles.joinToString(","))
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(Date())
            .setExpiration(expiredDate)
            .compact()
    }

    fun getBody(token: String): Claims = getJws(token).body

    fun verifyTokenOrThrownError(token: String?) = getJws(token)

    fun getRoles(claims: Claims): List<String> {
        return claims[ROLE_KEY].toString()
            .split(",")
            .dropLastWhile { it.isEmpty() }
            .toList()
    }

    private fun getJws(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
    }

    companion object : LogSupport {
        private const val ROLE_KEY = "role"
    }
}

data class CreateTokenRequest(
    val subject: String,
    val roles: List<String>,
) {
    companion object {
        fun from(authentication: Authentication) =
            CreateTokenRequest(authentication.name, authentication.authorities.map { it.authority })

        fun from(claims: Claims) =
            CreateTokenRequest(claims.subject, claims["role", String::class.java].split(","))
    }
}

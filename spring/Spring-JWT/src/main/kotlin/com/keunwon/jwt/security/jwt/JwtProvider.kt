package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.annotation.JsonFormat
import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.UserToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
@EnableConfigurationProperties(JwtProperties::class)
class JwtProvider(
    @Value("\${spring.application.name}") private val appName: String,
    private val jwtProperties: JwtProperties,
) : InitializingBean {
    private lateinit var key: Key

    override fun afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret))
    }

    fun generateLoginSuccessToken(tokenRequest: CreateTokenRequest): JwtResult {
        val accessToken = generateToken(tokenRequest, jwtProperties.expiredDateByAccessToken)
        val refreshToken = generateToken(tokenRequest, jwtProperties.expirationDateByRefreshToken)
        return JwtResult(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expirationAccessToken = getExpirationLocalDateTime(accessToken),
            expirationRefreshToken = getExpirationLocalDateTime(refreshToken),
        )
    }

    fun generateAccessTokenBy(refreshToken: String): String {
        verifyTokenOrThrownError(refreshToken)
        val claims = getJws(refreshToken).body
        return generateToken(CreateTokenRequest.from(claims), jwtProperties.expiredDateByAccessToken)
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

    fun getExpirationLocalDateTime(token: String): LocalDateTime {
        return getJws(token)
            .body.expiration.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

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

data class JwtResult(
    val accessToken: String,
    val refreshToken: String,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") val expirationAccessToken: LocalDateTime,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") val expirationRefreshToken: LocalDateTime,
) {
    fun toEntity(userid: Long) = UserToken(
        userId = userid,
        refreshToken = refreshToken,
        expiredRefreshToken = expirationRefreshToken,
    )
}

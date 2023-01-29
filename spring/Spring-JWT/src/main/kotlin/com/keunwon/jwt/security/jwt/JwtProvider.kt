package com.keunwon.jwt.security.jwt

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

    fun generateLoginSuccessToken(authentication: Authentication): TokenIssue {
        val dto = CreateJwtDto(authentication.name, authentication.authorities.map { it.authority })
        val accessToken = generateToken(dto, jwtProperties.expiredDateByAccessToken)
        val refreshToken = generateToken(dto, jwtProperties.expirationDateByRefreshToken)
        return TokenIssue(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expirationAccessToken = getExpirationLocalDateTime(accessToken),
            expirationRefreshToken = getExpirationLocalDateTime(refreshToken),
        )
    }

    fun generateAccessTokenBy(refreshToken: String): String {
        verifyTokenOrThrownError(refreshToken)
        val claims = getJws(refreshToken).body
        return generateToken(CreateJwtDto(claims), jwtProperties.expiredDateByAccessToken)
    }

    fun generateToken(dto: CreateJwtDto, expiredDate: Date): String {
        return Jwts.builder()
            .setIssuer(appName)
            .setSubject(dto.subject)
            .claim(ROLE_KEY, dto.roles.joinToString(","))
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

data class CreateJwtDto(
    val subject: String,
    val roles: List<String>,
) {
    constructor(claims: Claims) : this(claims.subject, claims["role", String::class.java].split(","))
}

data class TokenIssue(
    val accessToken: String,
    val refreshToken: String,
    val expirationAccessToken: LocalDateTime,
    val expirationRefreshToken: LocalDateTime,
) {

    fun toEntity(userId: Long) = UserToken(
        userId = userId,
        refreshToken = refreshToken,
        expiredRefreshToken = expirationRefreshToken,
    )
}

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
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

    fun createTokenIssue(authentication: Authentication): TokenIssue {
        val accessToken = generateAccessToken(authentication)
        val refreshToken = generateRefreshToken(authentication)
        return TokenIssue(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expirationAccessToken = expirationLocalDateTime(accessToken),
            expirationRefreshToken = expirationLocalDateTime(refreshToken),
        )
    }

    fun generateAccessToken(authentication: Authentication): String =
        generateToken(authentication, jwtProperties.expiredDateByAccessToken)

    fun generateRefreshToken(authentication: Authentication): String =
        generateToken(authentication, jwtProperties.expirationDateByRefreshToken)

    fun generateToken(authentication: Authentication, expiredDate: Date): String {
        val authorities = authentication.authorities.joinToString(",") { it.authority }
        return Jwts.builder()
            .setIssuer(appName)
            .setSubject(authentication.name)
            .claim(ROLE_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setIssuedAt(Date())
            .setExpiration(expiredDate)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        val claims = getJws(token).body
        val authorities = getGrantedAuthorities(claims)
        val user = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(user, token, authorities)
    }

    fun validationToken(token: String?): Boolean {
        return try {
            getJws(token)
            true
        } catch (e: Exception) {
            log.error("> 토큰 검증 중 오류 발생: {}", e.message)
            false
        }
    }

    fun verifyTokenWithThrows(token: String?) = getJws(token)

    fun expirationLocalDateTime(token: String): LocalDateTime {
        return getJws(token)
            .body.expiration.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    private fun getJws(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
    }

    private fun getGrantedAuthorities(claims: Claims): List<GrantedAuthority> {
        return claims[ROLE_KEY].toString()
            .split(",")
            .dropLastWhile { it.isEmpty() }
            .map { SimpleGrantedAuthority(it) }
            .toList()
    }

    companion object : LogSupport {
        private const val ROLE_KEY = "role"
    }
}

data class TokenIssue(
    val accessToken: String,
    val refreshToken: String,
    val expirationAccessToken: LocalDateTime,
    val expirationRefreshToken: LocalDateTime,
) {

    fun toEntity(userId: Long) = UserToken(
        userId = userId,
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiredAccessToken = expirationAccessToken,
        expiredRefreshToken = expirationRefreshToken,
    )
}

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

    fun getAuthentication(token: String?): Authentication {
        val claims = getJws(token).body
        val authorities = getGrantedAuthorities(claims)
        val user = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(user, token, authorities)
    }

    fun verifyTokenOrThrownError(token: String?) = getJws(token)

    fun getExpirationLocalDateTime(token: String): LocalDateTime {
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
            .map(::SimpleGrantedAuthority)
            .toList()
    }

    companion object : LogSupport {
        private const val ROLE_KEY = "role"
    }
}

data class CreateJwtDto(
    val subject: String,
    val roles: List<String>,
)

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

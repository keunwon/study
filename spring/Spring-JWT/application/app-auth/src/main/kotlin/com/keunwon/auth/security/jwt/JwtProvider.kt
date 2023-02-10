package com.keunwon.auth.security.jwt

import com.keunwon.auth.config.LogSupport
import com.keunwon.auth.domain.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
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

    fun generateLoginSuccessToken(claimsInfo: ClaimsInfo): JwtLoginToken {
        return JwtLoginToken(generateAccessToken(claimsInfo), generateRefreshToken(claimsInfo))
    }

    fun generateAccessTokenBy(refreshToken: String): JwtAccessToken {
        val claims = getClaims(refreshToken)
        return generateAccessToken(ClaimsInfo.from(claims))
    }

    private fun generateAccessToken(claimsInfo: ClaimsInfo): JwtAccessToken {
        val tokenValue = generateToken(claimsInfo, jwtProperties.expiredDateByAccessToken)
        val claims = getClaims(tokenValue)
        return JwtAccessToken(tokenValue, claims)
    }

    private fun generateRefreshToken(claimsInfo: ClaimsInfo): JwtRefreshToken {
        val tokenValue = generateToken(claimsInfo, jwtProperties.expirationDateByRefreshToken)
        val claims = getClaims(tokenValue)
        return JwtRefreshToken(tokenValue, claims)
    }

    fun generateToken(claimsInfo: ClaimsInfo, expirationDate: Date): String {
        return Jwts.builder()
            .setIssuer(appName)
            .addClaims(claimsInfo.toMap())
            .signWith(key, SignatureAlgorithm.HS256)
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .compact()
    }

    fun getClaims(token: String): Claims = getJwsClaims(token).body

    fun verifyTokenOrThrown(token: String?) = getJwsClaims(token)

    private fun getJwsClaims(token: String?): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
    }

    companion object : LogSupport {
        private const val ROLE_KEY = "role"
        val validationErrorMessages = mapOf(
            UnsupportedJwtException::class to "토큰 형식이 올바르지 않습니다.",
            MalformedJwtException::class to "토큰이 유효하지 않습니다.",
            SignatureException::class to "시그니처 연산에 실패하였습니다.",
            ExpiredJwtException::class to "유효기간이 만료된 토큰입니다.",
            IllegalArgumentException::class to "토큰이 비어있거나 존재하지 않습니다.",
        )
    }
}

data class ClaimsInfo(
    val userId: Long,
    val subject: String,
    val roles: List<String>,
) {
    constructor(user: User) : this(
        user.id,
        user.information.email,
        listOf(user.role.name),
    )

    fun getGrantedAuthorities(): List<GrantedAuthority> = roles.map { SimpleGrantedAuthority(it) }

    fun toMap(): Map<String, Any> {
        return mapOf(
            ID to userId,
            SUB to subject,
            ROLES to roles.joinToString(","),
        )
    }

    companion object {
        const val ID = "id"
        const val SUB = "sub"
        const val ROLES = "roles"

        fun from(claims: Claims): ClaimsInfo {
            val roles = claims[ROLES]?.let { value ->
                value.toString().split(",").dropLastWhile { it.isBlank() }
            } ?: emptyList()
            return ClaimsInfo(
                claims[ID].toString().toLong(),
                claims.subject,
                roles,
            )
        }
    }
}

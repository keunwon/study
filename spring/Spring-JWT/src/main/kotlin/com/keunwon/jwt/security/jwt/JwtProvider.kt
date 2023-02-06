package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.config.LogSupport
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
        return JwtAccessToken(tokenValue, claims)
    }

    fun generateRefreshToken(tokenRequest: CreateTokenRequest): JwtRefreshToken {
        val tokenValue = generateToken(tokenRequest, jwtProperties.expirationDateByRefreshToken)
        val claims = getBody(tokenValue)
        return JwtRefreshToken(tokenValue, claims)
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

    fun getBody(token: String): Claims = getJwsClaims(token).body

    fun verifyTokenOrThrownError(token: String?) = getJwsClaims(token)

    fun getRoles(claims: Claims): List<String> {
        return claims[ROLE_KEY].toString()
            .split(",")
            .dropLastWhile { it.isEmpty() }
            .toList()
    }

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

data class CreateTokenRequest(
    val subject: String,
    val roles: List<String>,
) {
    companion object {
        fun from(authentication: Authentication) =
            CreateTokenRequest(authentication.name, authentication.authorities.map { it.authority })
    }
}

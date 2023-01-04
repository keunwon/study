package com.keunwon.jwt.jwt

import com.keunwon.jwt.config.LogSupport
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class TokenProvider(
    @Value("\${spring.application.name}") private val appName: String,
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.token-validity-in-seconds}") tokenValidityInSecond: Long,
) : InitializingBean {
    private val tokenValidityInMilliseconds: Long = tokenValidityInSecond * 1000
    private var key: Key? = null

    override fun afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
    }

    fun generateToken(authentication: Authentication, expiredDate: Date = createNowExpiredDate()): String {
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

    private fun createNowExpiredDate(): Date = Date(Date().time + tokenValidityInMilliseconds)

    companion object : LogSupport {
        private const val ROLE_KEY = "role"
    }
}

package com.github.keunwon.userauth.jwt

import com.github.keunwon.core.enums.UserRole
import com.github.keunwon.core.toDate
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.userauth.jwtPropertiesFixture
import com.github.keunwon.userauth.jwtProviderFixture
import io.jsonwebtoken.ExpiredJwtException
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class JwtProviderTest : StringSpec({
    "accessToken 생성합니다." {
        val jwtClaims = JwtClaims.accessToken(
            sub = "test@test.com",
            id = 1L,
            role = UserRole.MEMBER,
        )

        val accessToken = jwtProviderFixture.createAccessToken(jwtClaims)
        val claims = jwtProviderFixture.getJwsClaims(accessToken.value).body

        shouldNotThrowAny { jwtProviderFixture.validateToken(accessToken.value) }
        claims.subject shouldBe jwtClaims.sub
        claims.issuer shouldBe issuer
        claims["id"].toString().toLong() shouldBe jwtClaims.id
        claims["role"] as String shouldBe jwtClaims.role!!.name
    }

    "refreshToken 생성합니다." {
        val jwtClaims = JwtClaims.refreshToken("test@test.com")

        val refreshToken = jwtProviderFixture.createRefreshToken(jwtClaims)
        val claims = jwtProviderFixture.getJwsClaims(refreshToken.value).body

        shouldNotThrowAny { jwtProviderFixture.validateToken(refreshToken.value) }
        claims.subject shouldBe jwtClaims.sub
        claims.issuer shouldBe issuer
        claims["id"].shouldBeNull()
        claims["role"].shouldBeNull()
    }

    "loginToken 생성합니다." {
        val user = UserBuilder(id = 1L).build()
        val (accessToken, refreshToken) = jwtProviderFixture.createLoginToken(JwtClaims.loginToken(user))

        shouldNotThrowAny {
            jwtProviderFixture.validateToken(accessToken.value)
            jwtProviderFixture.validateToken(refreshToken.value)
        }
    }

    "만료된 토큰을 검증 시 오류가 발생합니다." {
        val expiredDateTime = LocalDateTime.of(2022, 1, 1, 0, 0)

        val token = jwtProviderFixture.createTokenString(emptyMap(), expiredDateTime.toDate())

        shouldThrowExactly<ExpiredJwtException> {
            jwtProviderFixture.validateToken(token)
        }
    }
}) {
    companion object {
        private val issuer = jwtPropertiesFixture.issuer
    }
}

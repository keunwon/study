package com.keunwon.jwt.jwt

import com.keunwon.jwt.TokenProviderFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*

internal class TokenProviderTest {

    @Test
    fun `문자열 토큰을 발급`() {
        // given
        val token = tokenProvider.generateAccessToken(authentication)

        // when, then
        assertAll({
            assertThat(token).isNotEmpty
            assertThat(token.length).isGreaterThan(100)
        })
    }

    @Test
    fun `정상적인 토큰을 검증하면 true 반환`() {
        // given
        val token = tokenProvider.generateAccessToken(authentication)

        // when, then
        assertThat(tokenProvider.validationToken(token)).isTrue
    }

    @Test
    fun `기간이 지난 토큰 검증 시 false 반환`() {
        // given
        val expiredDate = Date(Date().time - 1000)
        val token = tokenProvider.generateToken(authentication, expiredDate)

        // when, then
        assertThat(tokenProvider.validationToken(token))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "  ", "trash.trash"])
    fun `비어있거나, 유효하지 않은 토큰을 검증하면 false 반환`(token: String) {
        assertThat(tokenProvider.validationToken(token)).isFalse
    }

    @Test
    fun `문자열 토큰을 Authentication 변환`() {
        // given
        val token = tokenProvider.generateAccessToken(authentication)

        // when
        val auth = tokenProvider.getAuthentication(token)

        // then
        assertAll({
            assertThat(auth.principal).isEqualTo(user)
            assertThat(auth.credentials).isEqualTo(token)
            assertThat(auth.authorities.joinToString { it.authority })
                .isEqualTo(user.authorities.joinToString { it.authority })
        })
    }

    companion object {
        private val tokenProvider = TokenProviderFixture.testTokenProvider
        private val user = User("user", "", listOf(SimpleGrantedAuthority("USER")))
        private val authentication = UsernamePasswordAuthenticationToken(user, "", user.authorities)
    }
}

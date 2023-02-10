package com.keunwon.auth.security.jwt

import com.keunwon.auth.common.UserRole
import com.keunwon.auth.createToken
import com.keunwon.auth.testTokenProvider
import io.jsonwebtoken.ExpiredJwtException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class JwtProviderTest {

    @Test
    fun `문자열 토큰을 발급`() {
        // given
        val token = createToken(expired = false).value

        // when, then
        assertAll({
            assertThat(token).isNotEmpty
            assertThat(token.length).isGreaterThan(100)
        })
    }

    @Test
    fun `정상적인 토큰 검증 시 exception 발생하지 않습니다`() {
        // given
        val token = createToken(expired = false).value

        // when, then
        assertDoesNotThrow { testTokenProvider.verifyTokenOrThrown(token) }
    }

    @Test
    fun `기간이 지난 토큰 검증 시 exception 발생합니다`() {
        // given
        val token = createToken(expired = true).value

        // when, then
        assertThatThrownBy { testTokenProvider.verifyTokenOrThrown(token) }
            .isInstanceOf(ExpiredJwtException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "  ", "trash.trash"])
    fun `비어있거나, 유효하지 않은 토큰을 검증하면 exception 발생합니다`(token: String) {
        assertThatThrownBy { testTokenProvider.verifyTokenOrThrown(token) }
    }

    @Test
    fun `문자열 토큰을 Claims 반환합니다`() {
        // given
        val token = createToken(expired = false).value

        // when
        val claims = testTokenProvider.getClaims(token)
        val claimsInfo = ClaimsInfo.from(claims)

        // then
        assertAll({
            assertThat(claims.subject).isEqualTo("test@test.com")
            assertThat(claimsInfo.roles).isEqualTo(listOf(UserRole.USER.name))
        })
    }
}

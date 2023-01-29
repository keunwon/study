package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.TokenProviderFixture.expiredDate
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.TokenProviderFixture.unExpiredDate
import com.keunwon.jwt.domain.UserRole
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
        val token = testTokenProvider.generateToken(createJwtDto, unExpiredDate)

        // when, then
        assertAll({
            assertThat(token).isNotEmpty
            assertThat(token.length).isGreaterThan(100)
        })
    }

    @Test
    fun `정상적인 토큰 검증 시 exception 발생하지 않습니다`() {
        // given
        val token = testTokenProvider.generateToken(createJwtDto, unExpiredDate)

        // when, then
        assertDoesNotThrow { testTokenProvider.verifyTokenOrThrownError(token) }
    }

    @Test
    fun `기간이 지난 토큰 검증 시 exception 발생합니다`() {
        // given
        val token = testTokenProvider.generateToken(createJwtDto, expiredDate)

        // when, then
        assertThatThrownBy { testTokenProvider.verifyTokenOrThrownError(token) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "  ", "trash.trash"])
    fun `비어있거나, 유효하지 않은 토큰을 검증하면 exception 발생합니다`(token: String) {
        assertThatThrownBy { testTokenProvider.verifyTokenOrThrownError(token) }
    }

    @Test
    fun `문자열 토큰을 Claims 반환합니다`() {
        // given
        val token = testTokenProvider.generateToken(createJwtDto, unExpiredDate)

        // when
        val claims = testTokenProvider.getBody(token)

        // then
        assertAll({
            assertThat(claims.subject).isEqualTo(createJwtDto.subject)
            assertThat(testTokenProvider.getRoles(claims)).isEqualTo(createJwtDto.roles)
        })
    }

    companion object {
        private val createJwtDto = CreateJwtDto("test-id", UserRole.DEFAULT_ROLES)
    }
}

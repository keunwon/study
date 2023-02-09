package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.InmemoryUserTokenRepository
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.createToken
import com.keunwon.jwt.domain.USER_EMAIL
import com.keunwon.jwt.domain.UserBuilder
import com.keunwon.jwt.domain.UserTokenBuilder
import com.keunwon.jwt.security.jwt.AbstractJwtToken
import com.keunwon.jwt.testTokenProvider
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
class UserTokenServiceTest {
    private val userRepository = InmemoryUserRepository()
    private val userTokenRepository = InmemoryUserTokenRepository()
    private val userTokenService = UserTokenService(userRepository, userTokenRepository, testTokenProvider)

    @Test
    fun `refreshToken 이용하여 accessToken 재발행합니다`() {
        // given
        val email = USER_EMAIL
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = false)

        // when
        val token = userTokenService.refreshAccessToken(AccessTokenIssueRequest(email, refreshToken.value))

        // then
        assertAll({
            assertDoesNotThrow { testTokenProvider.verifyTokenOrThrown(token.value) }
            assertTrue(token.value.isNotBlank())
        })
    }

    @Test
    fun `refreshToken 값이 db와 다른 경우 오류가 발생합니다`() {
        // given
        val email = USER_EMAIL
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = false)
        val newRefreshToken = testTokenProvider.generateAccessTokenBy(refreshToken.value).value

        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssueRequest(email, newRefreshToken)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("refreshToken 일치하지 않습니다")
    }

    @Test
    fun `만료된 refreshToken 으로 재발급을 요청하면 오류가 발생합니다`() {
        // given
        val email = USER_EMAIL
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = true)

        // when, then
        assertThatThrownBy {
            userTokenService.refreshAccessToken(
                AccessTokenIssueRequest(email, refreshToken.value)
            )
        }
            .isInstanceOf(ExpiredJwtException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["dump", "dump1234"])
    fun `유효하지 않는 refreshToken 으로 재발급 요청을 하면 오류가 발생합니다`(refreshToken: String) {
        // given
        val email = USER_EMAIL
        givenLoginUserAndGetRefreshToken(expired = false)

        // when ,then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssueRequest(email, refreshToken)) }
            .isInstanceOf(JwtException::class.java)
    }

    private fun givenLoginUserAndGetRefreshToken(expired: Boolean): AbstractJwtToken {
        val user = UserBuilder(id = 1L).build().also { userRepository.save(it) }
        return createToken(
            userId = user.id,
            expired = expired,
        ).also { token ->
            val userToken = UserTokenBuilder(
                userId = user.id,
                refreshToken = token.value,
                expirationRefreshToken = token.expiredAt.toLocalDateTime(),
            ).build()
            userRepository.save(user)
            userTokenRepository.save(userToken)
        }
    }
}

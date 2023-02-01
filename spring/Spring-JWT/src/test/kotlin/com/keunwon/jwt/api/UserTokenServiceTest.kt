package com.keunwon.jwt.api

import com.keunwon.jwt.TokenProviderFixture.expiredDate
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.TokenProviderFixture.unExpiredDate
import com.keunwon.jwt.domain.LoginType
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.domain.UserToken
import com.keunwon.jwt.domain.UserTokenRepository
import com.keunwon.jwt.security.jwt.CreateTokenRequest
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime
import java.time.ZoneId

@ExtendWith(MockKExtension::class)
class UserTokenServiceTest {
    private val userRepository = mockkClass(UserRepository::class)
    private val userTokenRepository = mockkClass(UserTokenRepository::class)
    private val userTokenService = UserTokenService(userRepository, userTokenRepository, testTokenProvider)

    @Test
    fun `refreshToken 이용하여 accessToken 재발행합니다`() {
        // given
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = false)

        // when
        val token = userTokenService.refreshAccessToken(AccessTokenIssue(user.username!!, refreshToken))

        // then
        assertAll({
            assertDoesNotThrow { testTokenProvider.verifyTokenOrThrownError(token.value) }
            assertTrue(token.value.isNotBlank())
        })
    }

    @Test
    fun `refreshToken 값이 db와 다른 경우 오류가 발생합니다`() {
        // given
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = false)
        val newRefreshToken = testTokenProvider.generateAccessTokenWith(refreshToken).value

        // when, then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssue(user.username!!, newRefreshToken)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("refreshToken 일치하지 않습니다")
    }

    @Test
    fun `만료된 refreshToken 으로 재발급을 요청하면 오류가 발생합니다`() {
        // given
        val refreshToken = givenLoginUserAndGetRefreshToken(expired = true)

        // when, then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssue(user.username!!, refreshToken)) }
            .isInstanceOf(ExpiredJwtException::class.java)
    }

    @ParameterizedTest
    @ValueSource(strings = ["dump", "dump1234"])
    fun `유효하지 않는 refreshToken 으로 재발급 요청을 하면 오류가 발생합니다`(refreshToken: String) {
        // given
        givenLoginUserAndGetRefreshToken(expired = false)

        // when ,then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssue(user.username!!, refreshToken)) }
            .isInstanceOf(JwtException::class.java)
    }

    private fun givenLoginUserAndGetRefreshToken(expired: Boolean): String {
        val targetDate = if (expired) expiredDate else unExpiredDate
        val refreshToken =
            testTokenProvider.generateToken(CreateTokenRequest(user.username!!, listOf("USER")), targetDate)
        every { userRepository.findByUsername(user.username!!) } returns user
        every { userTokenRepository.findByUserId(user.id) } returns
                userToken(refreshToken, LocalDateTime.ofInstant(targetDate.toInstant(), ZoneId.systemDefault()))
        return refreshToken
    }

    private val user = User(
        id = 1L,
        username = "test-id",
        password = "password",
        name = "홍길동",
        nickname = "닉네임",
        role = UserRole.USER,
        loginType = LoginType.SIMPLE,
    )

    private fun userToken(refreshToken: String, dateTime: LocalDateTime) = UserToken(
        userId = user.id,
        refreshToken = refreshToken,
        expiredRefreshToken = dateTime,
    )
}

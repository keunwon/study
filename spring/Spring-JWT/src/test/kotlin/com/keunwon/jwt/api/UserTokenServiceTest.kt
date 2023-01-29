package com.keunwon.jwt.api

import com.keunwon.jwt.TokenProviderFixture.expiredDate
import com.keunwon.jwt.TokenProviderFixture.testTokenProvider
import com.keunwon.jwt.TokenProviderFixture.unExpiredDate
import com.keunwon.jwt.domain.LoginType
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.domain.UserToken
import com.keunwon.jwt.security.jwt.CreateJwtDto
import io.jsonwebtoken.ExpiredJwtException
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class UserTokenServiceTest {
    private val userRepository = mockkClass(UserRepository::class)
    private val userTokenService = UserTokenService(userRepository, testTokenProvider)

    @Test
    fun `refreshToken 이용하여 accessToken 재발행합니다`() {
        // given
        val userId = "test-id"
        val refreshToken = testTokenProvider.generateToken(CreateJwtDto(userId, listOf("USER")), unExpiredDate)
        val user = loginUser(refreshToken)
        every { userRepository.findByUsername(userId) } returns user

        // when
        val token = userTokenService.refreshAccessToken(AccessTokenIssue(userId, user.token!!.refreshToken))

        // then
        assertAll({
            assertDoesNotThrow { testTokenProvider.verifyTokenOrThrownError(token.accessToken) }
            assertTrue(token.accessToken.isNotBlank())
        })
    }

    @Test
    fun `refreshToken 값이 db와 다른 경우 오류가 발생합니다`() {
        // given
        val userId = "test-id"
        val refreshToken = testTokenProvider.generateToken(CreateJwtDto(userId, listOf("USER")), unExpiredDate)
        val user = loginUser(refreshToken)
        every { userRepository.findByUsername("test-id") } returns user

        // when, then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssue(userId, "")) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("refreshToken 일치하지 않습니다")
    }

    @Test
    fun `만료된 refreshToken 으로 재발급을 요청하면 오류가 발생합니다`() {
        // given
        val userId = "test-id"
        val refreshToken = testTokenProvider.generateToken(CreateJwtDto(userId, listOf("USER")), expiredDate)
        val user = loginUser(refreshToken)
        every { userRepository.findByUsername(userId) } returns user

        // when, then
        assertThatThrownBy { userTokenService.refreshAccessToken(AccessTokenIssue(userId, refreshToken)) }
            .isInstanceOf(ExpiredJwtException::class.java)
    }

    private fun loginUser(refreshToken: String): User {
        val userToken = UserToken(
            userId = 1L,
            refreshToken = refreshToken,
            expiredRefreshToken = LocalDateTime.now().plusHours(2L),
        )
        return User(
            id = 1L,
            username = "test-id",
            password = "password",
            name = "홍길동",
            nickname = "닉네임",
            role = UserRole.USER,
            loginType = LoginType.SIMPLE,
        ).apply { successLogin(userToken) }
    }
}

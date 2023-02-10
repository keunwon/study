package com.keunwon.auth.api

import com.keunwon.auth.InmemoryAuthenticationCodeRepository
import com.keunwon.auth.InmemoryUserPasswordHistoryRepository
import com.keunwon.auth.InmemoryUserRepository
import com.keunwon.auth.TestPasswordEncoder
import com.keunwon.auth.domain.AuthenticationCodeBuilder
import com.keunwon.auth.domain.INVALID_CODE
import com.keunwon.auth.domain.PasswordBuilder
import com.keunwon.auth.domain.USER_EMAIL
import com.keunwon.auth.domain.USER_NAME
import com.keunwon.auth.domain.USER_NICKNAME
import com.keunwon.auth.domain.USER_PASSWORD
import com.keunwon.auth.domain.UserBuilder
import com.keunwon.auth.domain.UserPasswordHistoryBuilder
import com.keunwon.auth.domain.user.Password
import com.keunwon.auth.domain.user.User
import com.keunwon.auth.domain.user.getByEmail
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrowsExactly
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDateTime

class UserAuthenticationServiceTest {
    private val userRepository = InmemoryUserRepository()
    private val authenticationCodeRepository = InmemoryAuthenticationCodeRepository()
    private val userPasswordHistoryRepository = InmemoryUserPasswordHistoryRepository()
    private val passwordEncoder = TestPasswordEncoder

    private val userAuthenticationService = UserAuthenticationService(
        userRepository,
        authenticationCodeRepository,
        userPasswordHistoryRepository,
        passwordEncoder,
    )

    @Test
    fun `신규 사용자 회원가입`() {
        // given
        val authenticationCode = AuthenticationCodeBuilder(authenticated = true).build()
        val request = UserSignRequest(
            password = USER_PASSWORD,
            confirmPassword = USER_PASSWORD,
            email = USER_EMAIL,
            name = USER_NAME,
            nickname = USER_NICKNAME,
            authenticationCode = authenticationCode.code,
        )
        authenticationCodeRepository.save(authenticationCode)

        // when, then
        assertDoesNotThrow { userAuthenticationService.register(request) }
        with(userRepository.getByEmail(request.email).information) {
            assertAll({
                assertThat(name).isEqualTo(request.name)
                assertThat(nickname).isEqualTo(request.nickname)
                assertThat(email).isEqualTo(request.email)
            })
        }
    }

    @Test
    fun `인증 코드를 생성합니다`() {
        // given
        val email = USER_EMAIL

        // when
        val code = userAuthenticationService.generateAuthenticationCode(email)

        // then
        assertTrue(code.isNotBlank())
    }

    @Test
    fun `인증 코드를 검증을 성공합니다`() {
        // given
        val authenticationCode = AuthenticationCodeBuilder(id = 1L).build()
        authenticationCodeRepository.save(authenticationCode)

        // when, then
        assertDoesNotThrow {
            userAuthenticationService.authenticateEmail(authenticationCode.email, authenticationCode.code)
        }
        assertThat(authenticationCode.authenticated).isTrue
    }

    @Test
    fun `인증 코드가 다른 경우 오류가 발생합니다`() {
        // given
        val invalidCode = INVALID_CODE
        val authenticationCode = AuthenticationCodeBuilder(id = 1L).build()
        authenticationCodeRepository.save(authenticationCode)

        // when
        val actuator = assertThrowsExactly(IllegalArgumentException::class.java) {
            userAuthenticationService.authenticateEmail(authenticationCode.email, invalidCode)
        }

        // then
        assertAll({
            assertThat(actuator.message).isEqualTo("인증코드가 일치하지 않습니다.")
            assertFalse(authenticationCode.authenticated)
        })
    }

    @Test
    fun `이미 인증하였으면 오류가 발생합니다`() {
        // given
        val authenticationCode = AuthenticationCodeBuilder(authenticated = true).build()
        authenticationCodeRepository.save(authenticationCode)

        // when
        val actuator = assertThrowsExactly(IllegalStateException::class.java) {
            userAuthenticationService.authenticateEmail(authenticationCode.email, authenticationCode.code)
        }

        // then
        assertAll({
            assertThat(authenticationCode.authenticated).isTrue
            assertTrue(actuator.message == "이미 인증되었습니다.")
        })
    }

    @Test
    fun `인증코드가 만료되었으면 오류가 발생합니다`() {
        // given
        val authenticationCode = AuthenticationCodeBuilder(
            id = 1L,
            createAt = LocalDateTime.now().minusMinutes(11L)
        ).build()
        authenticationCodeRepository.save(authenticationCode)

        // when
        val actuator = assertThrowsExactly(IllegalStateException::class.java) {
            userAuthenticationService.authenticateEmail(authenticationCode.email, authenticationCode.code)
        }

        // then
        assertAll({
            assertThat(actuator.message).isEqualTo("인증코드가 만료되었습니다.")
            assertFalse(authenticationCode.authenticated)
        })
    }

    @Test
    fun `사용자 비밀번호를 변경합니다`() {
        // given
        val user = givenSaveUserWithPasswordHistoryAndGet()
        val newPassword = Password("NEW_PASSWORD")
        val editUserPassword = EditUserPassword(user.id, user.password!!, newPassword)

        // when
        userAuthenticationService.changePassword(editUserPassword)

        // then
        assertDoesNotThrow { user.authenticate(newPassword, passwordEncoder) }
    }

    @Test
    fun `이전에 사용한 비밀번호로 변경시 오류가 발생합니다`() {
        // given
        val user = givenSaveUserWithPasswordHistoryAndGet()
        val oldPassword = Password(USER_PASSWORD)
        val edit = EditUserPassword(user.id, user.password!!, oldPassword)

        // when,
        assertThatThrownBy {
            userAuthenticationService.changePassword(edit)
        }.hasMessage("이미 사용했었던 비밀번호입니다.")
    }

    private fun givenSaveUserWithPasswordHistoryAndGet(): User {
        val user = UserBuilder(id = 1L).build()
        val baseLocalDateTime = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
        val passwordHistory = mutableListOf(
            UserPasswordHistoryBuilder(
                userId = user.id,
                password = PasswordBuilder("PASSWORD_1").build(),
                createdAt = baseLocalDateTime.minusMinutes(6L),
                id = 1L,
            ).build(),
            UserPasswordHistoryBuilder(
                userId = user.id,
                password = PasswordBuilder("PASSWORD_2").build(),
                createdAt = baseLocalDateTime.minusMinutes(3L),
                id = 2L,
            ).build(),
            UserPasswordHistoryBuilder(
                userId = user.id,
                createdAt = baseLocalDateTime,
            ).build(),
        )
        userRepository.save(user)
        userPasswordHistoryRepository.saveAll(passwordHistory)
        return user
    }
}

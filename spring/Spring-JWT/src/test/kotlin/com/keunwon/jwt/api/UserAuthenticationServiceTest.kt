package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryAuthenticationCodeRepository
import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.domain.AuthenticationCodeBuilder
import com.keunwon.jwt.domain.INVALID_CODE
import com.keunwon.jwt.domain.USERNAME
import com.keunwon.jwt.domain.USER_EMAIL
import com.keunwon.jwt.domain.USER_FULL_NAME
import com.keunwon.jwt.domain.USER_NICKNAME
import com.keunwon.jwt.domain.USER_PASSWORD
import com.keunwon.jwt.domain.getByUsername
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrowsExactly
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDateTime

class UserAuthenticationServiceTest {
    private val userRepository = InmemoryUserRepository()
    private val passwordEncoder = TestPasswordEncoder
    private val authenticationCodeRepository = InmemoryAuthenticationCodeRepository()

    private val userAuthenticationService =
        UserAuthenticationService(userRepository, authenticationCodeRepository, passwordEncoder)

    @Test
    fun `신규 사용자 회원가입`() {
        // given
        val request = UserSignRequest(USERNAME, USER_PASSWORD, USER_FULL_NAME, USER_NICKNAME)

        // when, then
        assertDoesNotThrow { userAuthenticationService.register(request) }
        with(userRepository.getByUsername(USERNAME)) {
            assertAll({
                assertTrue(username == USERNAME)
                assertTrue(matchPassword(USER_PASSWORD, passwordEncoder))
                assertTrue(name == USER_FULL_NAME)
                assertTrue(nickname == USER_NICKNAME)
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
            assertThat(actuator.message).isEqualTo("인증 코드가 일치하지 않습니다.")
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
}

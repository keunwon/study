package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryAuthenticationCodeRepository
import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.domain.AuthenticationCodeBuilder
import com.keunwon.jwt.domain.USERNAME
import com.keunwon.jwt.domain.USER_EMAIL
import com.keunwon.jwt.domain.USER_FULL_NAME
import com.keunwon.jwt.domain.USER_NICKNAME
import com.keunwon.jwt.domain.USER_PASSWORD
import com.keunwon.jwt.domain.getByUsername
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

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
        assertDoesNotThrow { userAuthenticationService.sign(request) }
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

        // when, then
        val code = userAuthenticationService.generateAuthenticationCode(email)
        assertTrue(code.isNotBlank())
    }

    @Test
    fun `인증 코드를 검증합니다`() {
        // given
        val authenticationCode = AuthenticationCodeBuilder().build()
        authenticationCodeRepository.save(authenticationCode)

        // when, then
        userAuthenticationService.authenticateEmail(authenticationCode.email, authenticationCode.code)
        assertThat(authenticationCode.authenticated).isTrue
    }
}

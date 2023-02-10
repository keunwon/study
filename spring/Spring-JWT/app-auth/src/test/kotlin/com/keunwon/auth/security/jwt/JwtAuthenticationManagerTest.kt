package com.keunwon.auth.security.jwt

import com.keunwon.auth.InmemoryUserRepository
import com.keunwon.auth.TestPasswordEncoder
import com.keunwon.auth.common.UserRole
import com.keunwon.auth.createUsernamePasswordAuthenticationToken
import com.keunwon.auth.domain.LoginPolicyBuilder
import com.keunwon.auth.domain.PasswordBuilder
import com.keunwon.auth.domain.UserBuilder
import com.keunwon.auth.domain.WRONG_USER_PASSWORD
import com.keunwon.auth.domain.user.LoginPolicy
import com.keunwon.auth.domain.user.User
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.userdetails.UsernameNotFoundException

class JwtAuthenticationManagerTest {
    private val userRepository = InmemoryUserRepository()
    private val passwordEncoder = TestPasswordEncoder
    private val jwtAuthenticationManager = JwtAuthenticationManager(userRepository, passwordEncoder)

    @Test
    fun `사용자 로그인 성공`() {
        // given
        val user = UserBuilder(id = 1L).build()
        userRepository.save(user)

        // when
        val authentication = jwtAuthenticationManager.authenticate(createUsernamePasswordAuthenticationToken())
        val principal = authentication.principal as User

        // then
        assertAll({
            assertThat(principal).isEqualTo(user)
            assertThat((principal.information.email)).isEqualTo(user.information.email)
            assertThat(authentication.authorities.map { it.authority }).containsAll(UserRole.DEFAULT_ROLES)
        })
    }

    @Test
    fun `사용자가 존재하지 않으면 'InternalAuthenticationServiceException' 발생`() {
        thenThrownBy(UsernameNotFoundException::class.java)
    }

    @Test
    fun `사용자가 비밀번호가 일치하지 않으면 'BadCredentialsException' 발생`() {
        // given
        val password = PasswordBuilder(WRONG_USER_PASSWORD).build()
        userRepository.save(
            UserBuilder(password = password).build()
        )

        // when, then
        thenThrownBy(BadCredentialsException::class.java)
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        val loginPolicy = LoginPolicyBuilder(failedPasswordCount = LoginPolicy.MAX_PASSWORD_FAILURED_COUNT).build()
        userRepository.save(
            UserBuilder(loginPolicy = loginPolicy).build()
        )

        // when, then
        thenThrownBy(LockedException::class.java)
    }

    private fun thenThrownBy(exception: Class<*>) {
        assertThatThrownBy {
            jwtAuthenticationManager.authenticate(createUsernamePasswordAuthenticationToken())
        }.isInstanceOf(exception)
    }
}

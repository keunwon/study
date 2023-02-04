package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.USER_ACCOUNT_LOCKED
import com.keunwon.jwt.USER_WRONG_PASSWORD
import com.keunwon.jwt.createPreAuthenticationToken
import com.keunwon.jwt.createUser
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

class JwtAuthenticationManagerTest {
    private val userRepository: UserRepository = InmemoryUserRepository()
    private val passwordEncoder: PasswordEncoder = TestPasswordEncoder()
    private val jwtAuthenticationManager = JwtAuthenticationManager(userRepository, passwordEncoder)

    @Test
    fun `사용자 로그인 성공`() {
        // given
        val user = createUser(id = 1L)
        userRepository.save(user)

        // when
        val authentication = jwtAuthenticationManager.authenticate(createPreAuthenticationToken())
        val principal = authentication.principal as User

        // then
        assertAll({
            assertThat(principal).isEqualTo(user)
            assertThat((principal.username)).isEqualTo(user.username)
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
        userRepository.save(createUser(password = USER_WRONG_PASSWORD))

        // when, then
        thenThrownBy(BadCredentialsException::class.java)
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        userRepository.save(createUser(isAccountNonLocked = USER_ACCOUNT_LOCKED))

        // when, then
        thenThrownBy(LockedException::class.java)
    }

    private fun thenThrownBy(exception: Class<*>) {
        assertThatThrownBy {
            jwtAuthenticationManager.authenticate(createPreAuthenticationToken())
        }.isInstanceOf(exception)
    }
}

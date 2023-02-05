package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.createPreAuthenticationToken
import com.keunwon.jwt.domain.USER_ACCOUNT_LOCKED
import com.keunwon.jwt.domain.USER_WRONG_PASSWORD
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserBuilder
import com.keunwon.jwt.domain.UserRole
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
        userRepository.save(
            UserBuilder(password = USER_WRONG_PASSWORD).build()
        )

        // when, then
        thenThrownBy(BadCredentialsException::class.java)
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        userRepository.save(
            UserBuilder(isAccountNonLocked = USER_ACCOUNT_LOCKED).build()
        )


        // when, then
        thenThrownBy(LockedException::class.java)
    }

    private fun thenThrownBy(exception: Class<*>) {
        assertThatThrownBy {
            jwtAuthenticationManager.authenticate(createPreAuthenticationToken())
        }.isInstanceOf(exception)
    }
}

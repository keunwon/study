package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
internal class JwtAuthenticationManagerTest {
    private val userRepository = mockkClass(UserRepository::class)
    private val jwtAuthenticationManager = JwtAuthenticationManager(userRepository, passwordEncoder)

    @Test
    fun `사용자 로그인 성공`() {
        // given
        val user = generatedUser(username, password)
        every { userRepository.findByUsername(username) } returns user

        // when
        val authentication = jwtAuthenticationManager.authenticate(authenticationToken)

        // then
        assertAll({
            assertThat(authentication.name.toLong()).isEqualTo(user.id)
            assertThat(authentication.authorities.map { UserRole.valueOf(it.authority) })
                .containsExactly(UserRole.USER)
        })
    }

    @Test
    fun `사용자가 존재하지 않으면 'InternalAuthenticationServiceException' 발생`() {
        // given
        every { userRepository.findByUsername(username) } returns null

        // when, then
        thenThrownBy(UsernameNotFoundException::class.java)
    }

    @Test
    fun `사용자가 비밀번호가 일치하지 않으면 'BadCredentialsException' 발생`() {
        // given
        every { userRepository.findByUsername(username) } returns generatedUser(username, "trash")

        // when, then
        thenThrownBy(BadCredentialsException::class.java)
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        every { userRepository.findByUsername(username) } returns generatedUser(username, password, false)

        // when, then
        thenThrownBy(LockedException::class.java)
    }

    private fun thenThrownBy(exception: Class<*>) {
        assertThatThrownBy { jwtAuthenticationManager.authenticate(authenticationToken) }
            .isInstanceOf(exception)
    }

    companion object {
        val passwordEncoder = BCryptPasswordEncoder()
        val username = "test-id"
        val password = "password"
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)

        fun generatedUser(username: String, password: String, isActivated: Boolean = true) = User(
            name = "홍길동",
            username = username,
            password = passwordEncoder.encode(password),
            nickname = "닉네임",
            isActivated = isActivated,
            role = UserRole.USER
        )
    }
}

package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
internal class JwtAuthenticationManagerTest {
    lateinit var jwtAuthenticationManager: JwtAuthenticationManager

    @MockK
    lateinit var jwtUserDetailsService: JwtUserDetailsService<User, Long>

    @BeforeAll
    fun setup() {
        jwtAuthenticationManager = JwtAuthenticationManager(jwtUserDetailsService, passwordEncoder)
    }

    @Test
    fun `사용자 로그인 성공`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns
                generatedUser(authenticationToken.name, authenticationToken.credentials as String)

        // when
        val authentication = jwtAuthenticationManager.authenticate(authenticationToken)
        val user = authentication.principal as User

        // then
        assertThat(user.username).isEqualTo(authenticationToken.name)
        assertThat(user.name).isEqualTo("홍길동")
        assertThat(authentication.authorities).isEqualTo(generatedGrantedAuthorityList(UserRole.USER))
    }

    @Test
    fun `사용자가 존재하지 않으면 'InternalAuthenticationServiceException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns null

        // when, then
        thenThrownBy(UsernameNotFoundException::class.java)
    }

    @Test
    fun `사용자가 비밀번호가 일치하지 않으면 'BadCredentialsException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns
                generatedUser(authenticationToken.name, "trash")
        every { jwtUserDetailsService.save(any()) } returns mockkClass(User::class, relaxed = true)

        // when, then
        thenThrownBy(BadCredentialsException::class.java)
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns
                generatedUser(authenticationToken.name, authenticationToken.credentials as String, false)

        // when, then
        thenThrownBy(LockedException::class.java)
    }

    private fun thenThrownBy(exception: Class<*>) {
        assertThatThrownBy { jwtAuthenticationManager.authenticate(authenticationToken) }
            .isInstanceOf(exception)
    }

    companion object {
        val passwordEncoder = BCryptPasswordEncoder()
        val authenticationToken = UsernamePasswordAuthenticationToken("test-id", "password")

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

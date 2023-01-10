package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrowsExactly
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
internal class JwtAuthenticationManagerTest {
    lateinit var jwtAuthenticationManager: JwtAuthenticationManager
    @MockK lateinit var jwtUserDetailsService: JwtUserDetailsService<User, Long>

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

        // then
        assertAll({
            assertThat(authentication.principal).isEqualTo(authenticationToken.principal)
            assertThat(authentication.credentials).isEqualTo(authentication.credentials)
            assertThat(authentication.authorities).isEqualTo(generatedGrantedAuthorityList(UserRole.USER))
        })
    }

    @Test
    fun `사용자가 존재하지 않으면 'InternalAuthenticationServiceException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns null

        // when, then
        assertThrowsExactly(InternalAuthenticationServiceException::class.java,
            { jwtAuthenticationManager.authenticate(authenticationToken) },
             "사용자를 찾을 수 없습니다: 사용자: ${authenticationToken.name}")
    }

    @Test
    fun `사용자가 비밀번호가 일치하지 않으면 'BadCredentialsException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns
                generatedUser(authenticationToken.name, "trash")

        // when, then
        assertThrowsExactly(BadCredentialsException::class.java,
            { jwtAuthenticationManager.authenticate(authenticationToken) },
        "사용자 비밀번호가 일치하지 않습니다. 사용자: ${authenticationToken.name}")
    }

    @Test
    fun `사용자의 계정이 잠겨있으면 'LockedException' 발생`() {
        // given
        every { jwtUserDetailsService.findByAuthentication(authenticationToken) } returns
                generatedUser(authenticationToken.name, authenticationToken.credentials as String, false)

        // when, then
        assertThrowsExactly(LockedException::class.java,
            { jwtAuthenticationManager.authenticate(authenticationToken) },
            "사용자 계정이 잠겨있습니다. 사용자: ${authenticationToken.name}")
    }

    companion object {
        val passwordEncoder = BCryptPasswordEncoder()
        val authenticationToken = UsernamePasswordAuthenticationToken("홍길동", "password")

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

package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder

class JwtAuthenticationManager(
    private val customerUserDetailsService: JwtUserDetailsService<User, Long>,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        val user = customerUserDetailsService.findByAuthentication(authentication)
            ?: throw InternalAuthenticationServiceException("사용자를 찾을 수 없습니다. 사용자: ${authentication.name}")
        when {
            lockUser(user) -> throw LockedException("사용자 계정이 잠겨있습니다. 사용자: ${authentication.name}")

            !matchPassword(authentication, user) -> {
                customerUserDetailsService.save(user.apply { failLogin() })
                throw BadCredentialsException("사용자 비밀번호가 일치하지 않습니다. 사용자: ${authentication.name}")
            }
        }
        return UsernamePasswordAuthenticationToken(user, "", generatedGrantedAuthorityList(user.role))
    }

    private fun lockUser(user: User): Boolean = !user.isActivated

    private fun matchPassword(authentication: Authentication, user: User): Boolean {
        val password = authentication.credentials as String
        return passwordEncoder.matches(password, user.password)
    }
}

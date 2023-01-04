package com.keunwon.jwt.jwt

import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder

class JwtAuthenticationManager(
    private val customerUserDetailsService: CustomUserDetailsService<*, *>,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
        val user = customerUserDetailsService.findByAuthentication(authentication)
            ?.also { userContext.set(it) }
            ?: throw InternalAuthenticationServiceException("사용자를 찾을 수 없습니다. 사용자: ${authentication.name}")
        when {
            !matchPassword(authentication, user) ->
                throw BadCredentialsException("사용자 비밀번호가 일치하지 않습니다. 사용자: ${authentication.name}")

            lockUser(user) ->
                throw LockedException("사용자 계정이 잠겨있습니다. 사용자: ${authentication.name}")
        }
        return generatedAuthenticationToken(authentication, user)
    }

    private fun lockUser(user: AbstractCustomUser<*>): Boolean = !user.isActivated

    private fun matchPassword(authentication: Authentication, user: AbstractCustomUser<*>): Boolean {
        val password = authentication.credentials as String
        return passwordEncoder.matches(password, user.password)
    }

    private fun generatedAuthenticationToken(authentication: Authentication, user: AbstractCustomUser<*>) =
        UsernamePasswordAuthenticationToken.authenticated(
            authentication.principal, authentication.credentials, generatedGrantedAuthorityList(user.role))

    companion object {
        val userContext: ThreadLocal<AbstractCustomUser<*>> = ThreadLocal()
    }
}

package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

open class JwtAuthenticationManager(
    private val customerUserDetailsService: JwtUserDetailsService<User, Long>,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationManager {

    @Transactional(noRollbackFor = [BadCredentialsException::class])
    override fun authenticate(authentication: Authentication): Authentication {
        val user = customerUserDetailsService.findByAuthentication(authentication)
            ?: throw UsernameNotFoundException("존재하지 않는 사용자입니다. 사용자: ${authentication.principal}")
        return user.run {
            preAuthenticationCheck()
            passwordCheckWithUpdateIfLoginFail(authentication)
            generateAuthenticationToken()
        }
    }

    private fun User.preAuthenticationCheck() {
        if (isActivated.not()) throw LockedException("사용자 계정이 잠겨있습니다. 사용자: $username")
    }

    private fun User.passwordCheckWithUpdateIfLoginFail(authentication: Authentication) {
        val password = authentication.credentials as String
        if (!passwordEncoder.matches(password, this.password)) {
            loginFail()
            throw BadCredentialsException("사용자 비밀번호가 일치하지 않습니다. 사용자: $username")
        }
    }

    private fun User.loginFail() {
        failCount++
        isActivated = failCount <= 10
    }

    private fun User.generateAuthenticationToken(): AbstractAuthenticationToken =
        UsernamePasswordAuthenticationToken(this, "", generatedGrantedAuthorityList(this.role))
}

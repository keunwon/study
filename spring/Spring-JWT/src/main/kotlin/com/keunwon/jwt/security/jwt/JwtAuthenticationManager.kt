package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.LockedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

open class JwtAuthenticationManager(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : AuthenticationManager {
    @Transactional(noRollbackFor = [BadCredentialsException::class])
    override fun authenticate(authentication: Authentication): Authentication {
        val user = userRepository.findByUsername(authentication.name)
            ?: throw UsernameNotFoundException("${authentication.name} 찾을 수 없습니다")
        return user.run {
            preAuthenticationCheck()
            matchPasswordAndUpdate(authentication, passwordEncoder)
            generateAuthenticationToken()
        }
    }

    private fun User.preAuthenticationCheck() {
        if (isActivated.not()) throw LockedException("사용자 계정이 잠겨있습니다. 사용자: $username")
    }

    private fun User.matchPasswordAndUpdate(authentication: Authentication, passwordEncoder: PasswordEncoder) {
        val password = authentication.credentials as String
        if (matchPassword(password, passwordEncoder)) {
            reset()
        } else {
            incrementFailures()
            throw BadCredentialsException("${authentication.name} 비밀번호가 일치하지 않습니다")
        }
    }

    private fun User.generateAuthenticationToken() =
        UsernamePasswordAuthenticationToken.authenticated(this, "", generatedGrantedAuthorityList(role))

    companion object : LogSupport
}

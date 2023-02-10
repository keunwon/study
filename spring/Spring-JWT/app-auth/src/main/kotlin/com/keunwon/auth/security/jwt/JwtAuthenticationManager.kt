package com.keunwon.auth.security.jwt

import com.keunwon.auth.common.mapGrantedAuthority
import com.keunwon.auth.config.LogSupport
import com.keunwon.auth.domain.user.LoginPolicy
import com.keunwon.auth.domain.user.Password
import com.keunwon.auth.domain.user.User
import com.keunwon.auth.domain.user.UserRepository
import com.keunwon.auth.domain.user.matches
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
        val user = userRepository.findByInformationEmail(authentication.name)
            ?: throw UsernameNotFoundException("${authentication.name} 찾을 수 없습니다")
        return user.run {
            preAuthenticationCheck()
            matchPasswordAndUpdate(authentication, passwordEncoder)
            generateAuthenticationToken()
        }
    }

    private fun User.preAuthenticationCheck() = with(loginPolicy) {
        if (!isAccountNotLocked) {
            throw LockedException("최대 로그인 시도 횟수를 초과하였습니다. (${LoginPolicy.MAX_PASSWORD_FAILURED_COUNT}회)")
        }
    }

    private fun User.matchPasswordAndUpdate(authentication: Authentication, passwordEncoder: PasswordEncoder) {
        val requestPassword = Password(authentication.credentials as String)
        if (passwordEncoder.matches(requestPassword, this.password!!)) {
            loginPolicy.reset()
        } else {
            loginPolicy.loginFailed()
            throw BadCredentialsException("${information.email} 비밀번호가 일치하지 않습니다.")
        }
    }

    private fun User.generateAuthenticationToken() =
        UsernamePasswordAuthenticationToken.authenticated(this, "", mapGrantedAuthority(role))

    companion object : LogSupport
}

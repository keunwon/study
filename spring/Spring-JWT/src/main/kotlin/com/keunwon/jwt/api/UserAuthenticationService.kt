package com.keunwon.jwt.api

import com.keunwon.jwt.domain.authenticationcode.AuthenticationCode
import com.keunwon.jwt.domain.authenticationcode.AuthenticationCodeRepository
import com.keunwon.jwt.domain.authenticationcode.getLastByEmail
import com.keunwon.jwt.domain.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserAuthenticationService(
    private val userRepository: UserRepository,
    private val authenticationCodeRepository: AuthenticationCodeRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(request: UserSignRequest) {
        check(!userRepository.existsByUsername(request.username)) {
            "${request.username}는 현재 사용 중인 아이디 입니다"
        }
        userRepository.save(request.toEntity(passwordEncoder))
    }

    fun generateAuthenticationCode(email: String): String {
        check(userRepository.existsByEmail(email)) { "가입된 이메일이 존재하지 않습니다." }
        val authenticationCode = authenticationCodeRepository.save(AuthenticationCode(email))
        return authenticationCode.code
    }

    fun authenticateEmail(email: String, code: String) {
        val authenticationCode = authenticationCodeRepository.getLastByEmail(email)
        authenticationCode.authenticate(code)
    }
}

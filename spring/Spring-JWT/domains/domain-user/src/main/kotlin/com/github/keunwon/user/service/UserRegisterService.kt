package com.github.keunwon.user.service

import com.github.keunwon.user.authenticationcode.AuthenticationCode
import com.github.keunwon.user.authenticationcode.AuthenticationCodeRepository
import com.github.keunwon.user.authenticationcode.getLastByEmail
import com.github.keunwon.user.memeber.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class UserRegisterService(
    private val userRepository: UserRepository,
    private val authenticationCodeRepository: AuthenticationCodeRepository,
) {
    fun generateAuthenticationCode(email: String): String {
        require(!userRepository.existsByProfileEmail(email)) {
            "${email}는 이미 가입된 이메일입니다."
        }
        val authenticationCode = authenticationCodeRepository.save(AuthenticationCode(email))
        return authenticationCode.code
    }

    fun authenticateEmail(email: String, code: String) {
        val authenticationCode = authenticationCodeRepository.getLastByEmail(email)
        authenticationCode.authenticate(code, LocalDateTime.now())
    }

    fun register(userRegister: UserRegister) {
        require(userRegister.samePassword()) { "비밀번호가 일치하지 않습니다." }
        check(!userRepository.existsByProfileEmail(userRegister.email)) {
            "${userRegister.email}는 가입된 이메일입니다."
        }
        authenticationCodeRepository.getLastByEmail(userRegister.email).validate(userRegister.code)
        userRepository.save(userRegister.toUser())
    }
}

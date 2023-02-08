package com.keunwon.jwt.api

import com.keunwon.jwt.domain.authenticationcode.AuthenticationCode
import com.keunwon.jwt.domain.authenticationcode.AuthenticationCodeRepository
import com.keunwon.jwt.domain.authenticationcode.getLastByEmail
import com.keunwon.jwt.domain.user.UserPasswordHistory
import com.keunwon.jwt.domain.user.UserPasswordHistoryRepository
import com.keunwon.jwt.domain.user.UserRepository
import com.keunwon.jwt.domain.user.getById
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserAuthenticationService(
    private val userRepository: UserRepository,
    private val authenticationCodeRepository: AuthenticationCodeRepository,
    private val userPasswordHistoryRepository: UserPasswordHistoryRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(request: UserSignRequest) {
        require(request.password == request.confirmPassword) { "사용자 비밀번호가 일치하지 않습니다." }
        check(!userRepository.existsByUsername(request.username)) {
            "${request.username}는 현재 사용 중인 아이디 입니다"
        }
        authenticationCodeRepository.getLastByEmail(request.email).validate(request.authenticationCode)
        val user = userRepository.save(request.toEntity(passwordEncoder))
        userPasswordHistoryRepository.save(UserPasswordHistory(user))
    }

    fun generateAuthenticationCode(email: String): String {
        check(!userRepository.existsByEmail(email)) { "이미 가입된 이메일입니다." }
        val authenticationCode = authenticationCodeRepository.save(AuthenticationCode(email))
        return authenticationCode.code
    }

    fun authenticateEmail(email: String, code: String) {
        val authenticationCode = authenticationCodeRepository.getLastByEmail(email)
        authenticationCode.authenticate(code)
    }

    fun changePassword(userId: Long, password: String) {
        val user = userRepository.getById(userId)
        val usedPassword = userPasswordHistoryRepository.findAllByUserId(user.id)
            .sortedBy { it.createdAt }
            .any { passwordEncoder.matches(password, it.password) }
        require(!usedPassword) { "이전에 사용했었던 비밀번호 입니다." }
        user.changePassword(password, passwordEncoder)
        userPasswordHistoryRepository.save(UserPasswordHistory(user))
    }
}

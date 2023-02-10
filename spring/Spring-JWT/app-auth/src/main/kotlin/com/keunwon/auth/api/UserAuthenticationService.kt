package com.keunwon.auth.api

import com.keunwon.auth.domain.authenticationcode.AuthenticationCode
import com.keunwon.auth.domain.authenticationcode.AuthenticationCodeRepository
import com.keunwon.auth.domain.authenticationcode.getLastByEmail
import com.keunwon.auth.domain.user.Password
import com.keunwon.auth.domain.user.UserPasswordHistory
import com.keunwon.auth.domain.user.UserPasswordHistoryRepository
import com.keunwon.auth.domain.user.UserRepository
import com.keunwon.auth.domain.user.getById
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
        check(!userRepository.existsByInformationEmail(request.email)) { "${request.email}는 가입된 이메일입니다." }
        authenticationCodeRepository.getLastByEmail(request.email).validate(request.authenticationCode)
        userRepository.save(request.toEntity(passwordEncoder))
    }

    fun generateAuthenticationCode(email: String): String {
        check(!userRepository.existsByInformationEmail(email)) { "${email}는 이미 가입된 이메일입니다." }
        val authenticationCode = authenticationCodeRepository.save(AuthenticationCode(email))
        return authenticationCode.code
    }

    fun authenticateEmail(email: String, code: String) {
        val authenticationCode = authenticationCodeRepository.getLastByEmail(email)
        authenticationCode.authenticate(code)
    }

    fun changePassword(editUserPassword: EditUserPassword) {
        val user = userRepository.getById(editUserPassword.id).apply {
            require(!usedPassword(id, editUserPassword.newPassword)) { "이미 사용했었던 비밀번호입니다." }
            changePassword(editUserPassword.oldPassword, editUserPassword.newPassword, passwordEncoder)
        }
        val userPasswordHistory = UserPasswordHistory(user.id, editUserPassword.oldPassword)
        userPasswordHistoryRepository.save(userPasswordHistory)
    }

    private fun usedPassword(userId: Long, newPassword: Password): Boolean {
        return userPasswordHistoryRepository.findAllByUserId(userId)
            .sortedBy { it.createdAt }
            .any { passwordEncoder.matches(it.password.value, newPassword.value) }
    }
}

package com.github.keunwon.user.service

import com.github.keunwon.user.memeber.MissMatchUserPasswordException
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.PasswordEncrypt
import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.memeber.getByEmail
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserAuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncrypt: PasswordEncrypt,
) {
    @Transactional(rollbackFor = [MissMatchUserPasswordException::class])
    fun authenticate(email: String, password: Password, now: LocalDateTime = LocalDateTime.now()) {
        userRepository.getByEmail(email).let {
            it.authenticate(password, passwordEncrypt, now)
            log.info("> ${it.profile.email} 사용자 로그인 성공")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserAuthenticationService::class.java)
    }
}

package com.github.keunwon.user.service

import com.github.keunwon.user.memeber.NotMatchUserPasswordException
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.memeber.UserPasswordEncoder
import com.github.keunwon.user.memeber.UserRepository
import com.github.keunwon.user.memeber.getByEmail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UserAuthenticationService(
    private val userRepository: UserRepository,
    private val userPasswordEncoder: UserPasswordEncoder,
) {
    @Transactional(noRollbackFor = [NotMatchUserPasswordException::class])
    fun authenticate(
        email: String,
        password: Password,
        now: LocalDateTime = LocalDateTime.now(),
    ): Result<User> {
        return runCatching {
            userRepository.getByEmail(email).apply {
                authenticate(password, userPasswordEncoder, now)
            }
        }
    }
}

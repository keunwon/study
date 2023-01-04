package com.keunwon.jwt.api

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun sign(requestDto: UserDto): User {
        if (exitUser(requestDto.username)) throw IllegalArgumentException("현재 사용 중인 아이디 입니다.")
        return userRepository.save(requestDto.toEntity(passwordEncoder))
    }

    private fun exitUser(name: String): Boolean = userRepository.existsByUsername(name)
}
package com.keunwon.jwt.config

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.jwt.CustomUserDetailsService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication

class CustomUserDetailsServiceImpl(private val userRepository: UserRepository) : CustomUserDetailsService<User, Long> {
    override fun findLoginUser(id: Long): User = userRepository.findByIdOrNull(id)!!

    override fun findByAuthentication(authentication: Authentication): User? =
        userRepository.findByUsername(authentication.principal as String)

    override fun save(user: User): User = userRepository.save(user)
}

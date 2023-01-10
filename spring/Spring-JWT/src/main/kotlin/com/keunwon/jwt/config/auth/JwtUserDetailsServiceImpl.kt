package com.keunwon.jwt.config.auth

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.security.jwt.JwtUserDetailsService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.transaction.annotation.Transactional

@Transactional
class JwtUserDetailsServiceImpl(private val userRepository: UserRepository) : JwtUserDetailsService<User, Long> {
    override fun findLoginUser(id: Long): User = userRepository.findByIdOrNull(id)!!

    override fun findByAuthentication(authentication: Authentication): User? =
        userRepository.findByUsername(authentication.principal as String)

    override fun save(user: User): User = userRepository.save(user)
}

package com.keunwon.jwt.security.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.transaction.annotation.Transactional

interface JwtUserDetailsService<T : AbstractCustomUser<out ID>, ID> {
    fun findLoginUser(id: ID): T
    fun findByAuthentication(authentication: Authentication): T?
    fun save(user: T): T

    fun overLoginFailCount(failCount: Int): Boolean = failCount <= LOGIN_FAIL_COUNT

    companion object {
        const val LOGIN_FAIL_COUNT = 10
    }
}

abstract class AbstractCustomUser<ID> {
    abstract val id: ID?
    abstract val username: String
    abstract var password: String
    abstract var failCount: Int
    abstract var isActivated: Boolean
    abstract var role: UserRole
}

@Transactional
class JwtUserDetailsServiceImpl(private val userRepository: UserRepository) : JwtUserDetailsService<User, Long> {
    override fun findLoginUser(id: Long): User = userRepository.findByIdOrNull(id)!!

    override fun findByAuthentication(authentication: Authentication): User? =
        userRepository.findByUsername(authentication.principal as String)

    override fun save(user: User): User = userRepository.save(user)
}

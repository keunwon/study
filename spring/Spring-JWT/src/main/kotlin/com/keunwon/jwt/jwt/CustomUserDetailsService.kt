package com.keunwon.jwt.jwt

import com.keunwon.jwt.domain.UserRole
import org.springframework.security.core.Authentication

interface CustomUserDetailsService<T : AbstractCustomUser<out ID>, ID> {
    fun findLoginUser(id: ID): T
    fun findByAuthentication(authentication: Authentication): T?
    fun save(user: T): T

    fun successLogin(id: ID) = findLoginUser(id).apply {
        failCount = 0
    }

    fun incFailCount(id: ID) = findLoginUser(id).apply {
        failCount += 1
        isActivated = geMaxFailCount(failCount)
    }

    private fun geMaxFailCount(failCount: Int): Boolean = failCount <= LOGIN_FAIL_COUNT

    companion object {
        const val LOGIN_FAIL_COUNT = 10
    }
}

abstract class AbstractCustomUser<ID> {
    abstract var id: ID?
    abstract val username: String
    abstract var password: String
    abstract var failCount: Int
    abstract var isActivated: Boolean
    abstract var role: UserRole
}


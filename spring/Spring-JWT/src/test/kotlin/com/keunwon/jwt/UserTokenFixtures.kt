package com.keunwon.jwt

import com.keunwon.jwt.domain.UserToken
import com.keunwon.jwt.domain.UserTokenRepository
import java.time.LocalDateTime
import java.util.*

fun createUserToken(
    userId: Long,
    refreshToken: String,
    expirationRefreshToken: LocalDateTime,
): UserToken {
    return UserToken(userId, refreshToken, expirationRefreshToken)
}

class InmemoryUserTokenRepository : InmemoryRepository<UserToken>(), UserTokenRepository {
    override fun findById(id: Long): Optional<UserToken> {
        return super.findById(id)
    }

    override fun existsById(id: Long): Boolean {
        return super.existsById(id)
    }

    override fun deleteById(id: Long) {
        super.deleteById(id)
    }

    override fun findByUserId(userId: Long): UserToken? {
        return findAll().find { it.userId == userId }
    }
}

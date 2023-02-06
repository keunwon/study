package com.keunwon.jwt

import com.keunwon.jwt.domain.usertoken.UserToken
import com.keunwon.jwt.domain.usertoken.UserTokenRepository
import java.util.*

class InmemoryUserTokenRepository : InmemoryRepository<UserToken>(), UserTokenRepository {
    override fun findById(id: Long): Optional<UserToken> = super.findById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun findByUserId(userId: Long): UserToken? = findAll().find { it.userId == userId }
}

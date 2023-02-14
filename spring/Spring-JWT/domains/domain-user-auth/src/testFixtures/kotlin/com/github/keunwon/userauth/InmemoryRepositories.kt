package com.github.keunwon.userauth

import com.github.keunwon.repository.InmemoryCrudRepository
import com.github.keunwon.repository.LongPrimaryKeyGenerator
import com.github.keunwon.userauth.usertoken.UserToken
import com.github.keunwon.userauth.usertoken.UserTokenRepository

class InmemoryUserTokenRepository
    : InmemoryCrudRepository<UserToken, Long>(LongPrimaryKeyGenerator()), UserTokenRepository {
    override fun findByUserId(userId: Long): UserToken? {
        return findAll().find { it.userId == userId }
    }
}

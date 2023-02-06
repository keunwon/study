package com.keunwon.jwt

import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserPasswordHistory
import com.keunwon.jwt.domain.user.UserPasswordHistoryRepository
import com.keunwon.jwt.domain.user.UserRepository
import com.keunwon.jwt.domain.user.UserToken
import com.keunwon.jwt.domain.user.UserTokenRepository
import java.util.*

class InmemoryUserRepository : InmemoryRepository<User>(), UserRepository {
    override fun findById(id: Long): Optional<User> = super.findById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun findByUsername(username: String): User? = findAll().find { it.username == username }

    override fun findByEmail(email: String): User? = findAll().find { it.email == email }

    override fun existsByUsername(username: String): Boolean = findAll().any { it.username == username }

    override fun existsByEmail(email: String): Boolean = findAll().any { it.email == email }
}

class InmemoryUserTokenRepository : InmemoryRepository<UserToken>(), UserTokenRepository {
    override fun findById(id: Long): Optional<UserToken> = super.findById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun findByUserId(userId: Long): UserToken? = findAll().find { it.userId == userId }
}

class InmemoryUserPasswordHistoryRepository : InmemoryRepository<UserPasswordHistory>(), UserPasswordHistoryRepository {
    override fun findById(id: Long): Optional<UserPasswordHistory> = super.findById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): UserPasswordHistory? {
        return findAll().filter { it.userId == userId }.maxByOrNull { it.createdAt }
    }

    override fun findAllByUserId(userId: Long): List<UserPasswordHistory> {
        return findAll().filter { it.userId == userId }
    }
}

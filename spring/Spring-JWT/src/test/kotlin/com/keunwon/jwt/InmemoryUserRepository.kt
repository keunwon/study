package com.keunwon.jwt

import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserRepository
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


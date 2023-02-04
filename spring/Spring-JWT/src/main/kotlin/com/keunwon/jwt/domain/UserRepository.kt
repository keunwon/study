package com.keunwon.jwt.domain

import org.springframework.data.repository.CrudRepository

fun UserRepository.getByUsername(username: String): User = findByUsername(username)
    ?: throw IllegalArgumentException("$username 찾을 수 없습니다.")

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}

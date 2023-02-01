package com.keunwon.jwt.domain

import org.springframework.data.jpa.repository.JpaRepository

fun UserRepository.getByUsername(username: String): User = findByUsername(username)
    ?: throw IllegalArgumentException("$username 찾을 수 없습니다.")

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun findByEmail(email: String): User?
}

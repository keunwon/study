package com.keunwon.jwt.domain.user

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun UserRepository.getByUsername(username: String): User = findByUsername(username)
    ?: throw IllegalArgumentException("$username 찾을 수 없습니다.")

fun UserRepository.getById(id: Long): User = findByIdOrNull(id)
    ?: throw IllegalArgumentException("$id 값이 존재하지 않습니다.")

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}

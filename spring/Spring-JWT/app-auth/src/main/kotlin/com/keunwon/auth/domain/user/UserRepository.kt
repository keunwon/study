package com.keunwon.auth.domain.user

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun UserRepository.getByEmail(email: String): User = findByInformationEmail(email)
    ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다. 이메일: $email")

fun UserRepository.getById(id: Long): User = findByIdOrNull(id)
    ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다. id: $id")

interface UserRepository : CrudRepository<User, Long> {
    fun findByInformationEmail(email: String): User?
    fun existsByInformationEmail(email: String): Boolean
}

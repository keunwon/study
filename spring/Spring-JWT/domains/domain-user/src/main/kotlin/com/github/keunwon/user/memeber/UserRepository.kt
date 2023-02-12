package com.github.keunwon.user.memeber

import org.springframework.data.repository.CrudRepository

fun UserRepository.getByEmail(email: String): User {
    return findByProfileEmail(email)
        ?: throw IllegalArgumentException("사용자 이메일이 존재하지 않습니다. email: $email")
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByProfileEmail(email: String): User?
    fun existsByProfileEmail(email: String): Boolean
}

package com.keunwon.auth.domain.user

import org.springframework.data.repository.CrudRepository

fun UserPasswordHistoryRepository.getLastByUserId(userId: Long): UserPasswordHistory {
    return findFirstByUserIdOrderByCreatedAtDesc(userId)
        ?: throw IllegalArgumentException("사용자 이력을 찾을 수 없습니다. userId: $userId")
}

interface UserPasswordHistoryRepository : CrudRepository<UserPasswordHistory, Long> {
    fun findFirstByUserIdOrderByCreatedAtDesc(userId: Long): UserPasswordHistory?
    fun findAllByUserId(userId: Long): List<UserPasswordHistory>
}

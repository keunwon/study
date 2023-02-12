package com.github.keunwon.user.passwordhistory

import org.springframework.data.repository.CrudRepository

fun UserPasswordHistoryRepository.getLastByUserId(userId: Long): UserPasswordHistory {
    return getFirstByUserIdOrderByCreatedDateTimeDesc(userId)
        ?: throw IllegalArgumentException("비밀번호 이력이 없습니다. userId: $userId")
}

interface UserPasswordHistoryRepository : CrudRepository<UserPasswordHistory, Long> {
    fun getFirstByUserIdOrderByCreatedDateTimeDesc(userId: Long): UserPasswordHistory?
    fun getAllByUserId(userId: Long): List<UserPasswordHistory>
}

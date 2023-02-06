package com.keunwon.jwt.domain

import com.keunwon.jwt.domain.user.UserPasswordHistory
import java.time.LocalDateTime

data class UserPasswordHistoryBuilder(
    val userId: Long = 0,
    val password: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0
) {
    fun build(): UserPasswordHistory {
        return UserPasswordHistory(
            userId = userId,
            password = password,
            createdAt = createdAt,
            id = id
        )
    }
}

package com.keunwon.jwt.domain

import com.keunwon.jwt.domain.user.Password
import com.keunwon.jwt.domain.user.UserPasswordHistory
import java.time.LocalDateTime

data class UserPasswordHistoryBuilder(
    val userId: Long = 0,
    val password: Password = PasswordBuilder(USER_PASSWORD).build(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0,
) {
    fun build(): UserPasswordHistory {
        return UserPasswordHistory(
            userId,
            password,
            createdAt,
            id,
        )
    }
}

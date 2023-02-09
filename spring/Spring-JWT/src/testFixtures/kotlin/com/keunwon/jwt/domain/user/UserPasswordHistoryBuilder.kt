package com.keunwon.jwt.domain.user

import com.keunwon.jwt.domain.PasswordBuilder
import com.keunwon.jwt.domain.USER_PASSWORD
import java.time.LocalDateTime

data class UserPasswordHistoryBuilder(
    val userId: Long = 0,
    val password: Password = PasswordBuilder(USER_PASSWORD).build(),
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

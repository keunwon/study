package com.github.keunwon.user

import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.passwordhistory.UserPasswordHistory
import java.time.LocalDateTime

data class UserPasswordHistoryBuilder(
    val userId: Long = 0,
    val password: Password = PasswordBuilder().build(),
    val createdDateTime: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0
) {
    fun build(): UserPasswordHistory {
        return UserPasswordHistory(
            userId = userId,
            password = password,
            createdDateTime = createdDateTime,
            id = id
        )
    }
}

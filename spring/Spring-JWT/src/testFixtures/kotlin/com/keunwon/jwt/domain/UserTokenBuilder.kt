package com.keunwon.jwt.domain

import com.keunwon.jwt.domain.user.UserToken
import java.time.LocalDateTime

data class UserTokenBuilder(
    val userId: Long = 0,
    val refreshToken: String = "",
    val expirationRefreshToken: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0
) {
    fun build(): UserToken {
        return UserToken(
            userId = userId,
            refreshToken = refreshToken,
            expirationRefreshToken = expirationRefreshToken,
            id = id
        )
    }
}

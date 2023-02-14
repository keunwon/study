package com.github.keunwon.userauth

import com.github.keunwon.userauth.usertoken.UserToken
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
            expiryDateTime = expirationRefreshToken,
            id = id,
        )
    }
}

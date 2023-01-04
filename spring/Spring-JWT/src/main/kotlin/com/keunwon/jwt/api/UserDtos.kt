package com.keunwon.jwt.api

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import org.springframework.security.crypto.password.PasswordEncoder

data class UserDto(
    val username: String,
    val password: String,
    val nickname: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder) = User(
        username = username,
        password = passwordEncoder.encode(password),
        nickname = nickname,
        isActivated = true,
        role = UserRole.USER,
    )
}

data class UserSignResponseDto(
    val id: Long,
    val username: String,
    val nickname: String,
) {
    constructor(user: User) : this(user.id!!, user.username, user.nickname)
}

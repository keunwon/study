package com.github.keunwon.userauth.jwt

import com.github.keunwon.core.enums.UserRole

class LoginUserDto(
    val email: String,
    val nickname: String,
    val role: UserRole,
)

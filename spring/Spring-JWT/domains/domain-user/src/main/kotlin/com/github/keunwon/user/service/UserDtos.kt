package com.github.keunwon.user.service

import com.github.keunwon.core.enums.LoginType
import com.github.keunwon.core.enums.UserRole
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.memeber.UserProfile

data class UserRegister(
    val email: String,
    val name: String,
    val nickname: String,
    val password: String,
    val confirmPassword: String,
    val code: String,
) {
    fun samePassword(): Boolean = password == confirmPassword

    fun toUser(): User {
        return User(
            UserProfile(email, name, nickname),
            Password(password),
            LoginType.SIMPLE,
            UserRole.MEMBER,
        )
    }
}

data class PasswordEdit(
    val email: String,
    val password: Password,
    val newPassword: Password,
) {
    fun samePassword(): Boolean = password.value == newPassword.value
}

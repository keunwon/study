package com.keunwon.jwt.domain

import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.domain.user.LoginType
import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserRole

const val USERNAME = "test-id"
const val USER_EMAIL = "test@test.com"
const val USER_PASSWORD = "password"
const val USER_WRONG_PASSWORD = "failure"
const val USER_FULL_NAME = "홍길동"
const val USER_NICKNAME = "닉네임"
const val USER_ACCOUNT_LOCKED = false
val USER_ROLE = UserRole.USER

data class UserBuilder(
    val username: String? = USERNAME,
    val password: String? = USER_PASSWORD,
    val name: String = USER_FULL_NAME,
    val nickname: String = USER_NICKNAME,
    val email: String? = USER_EMAIL,
    val loginType: LoginType = LoginType.SIMPLE,
    val failedPasswordCount: Int = 0,
    val isAccountNonLocked: Boolean = true,
    val role: UserRole = USER_ROLE,
    val id: Long = 0,
) {
    fun build(): User {
        return User(
            username = username,
            password = password,
            name = name,
            nickname = nickname,
            email = email,
            loginType = loginType,
            failedPasswordCount = failedPasswordCount,
            isAccountNonLocked = isAccountNonLocked,
            role = role,
            id = id
        )
    }
}

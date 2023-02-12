package com.github.keunwon.user

import com.github.keunwon.core.enums.LoginType
import com.github.keunwon.core.enums.UserRole
import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.memeber.UserProfile

val USER_LOGIN_TYPE = LoginType.SIMPLE
val USER_ROLE = UserRole.MEMBER

data class UserBuilder(
    val userProfile: UserProfile = UserInformationBuilder().build(),
    val password: Password = PasswordBuilder().build(),
    val loginType: LoginType = USER_LOGIN_TYPE,
    val role: UserRole = USER_ROLE,
    val id: Long = 0
) {
    fun build(): User {
        return User(
            profile = userProfile,
            password = password,
            loginType = loginType,
            role = role,
            id = id
        )
    }
}

const val USER_EMAIL = "test@test.com"
const val USER_NAME = "홍길동"
const val USER_NICKNAME = "닉네임"

data class UserInformationBuilder(
    val email: String = USER_EMAIL,
    val name: String = USER_NAME,
    val nickname: String = USER_NICKNAME,
) {
    fun build(): UserProfile {
        return UserProfile(
            email = email,
            name = name,
            nickname = nickname
        )
    }
}

const val USER_PASSWORD = "PASSWORD"
const val WRONG_USER_PASSWORD = "WRONG_PASSWORD"

data class PasswordBuilder(
    val value: String = USER_PASSWORD,
) {
    fun build(): Password {
        return Password(
            value = value
        )
    }
}

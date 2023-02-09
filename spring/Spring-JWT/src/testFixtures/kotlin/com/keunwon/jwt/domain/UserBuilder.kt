package com.keunwon.jwt.domain

import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.domain.user.LoginPolicy
import com.keunwon.jwt.domain.user.LoginType
import com.keunwon.jwt.domain.user.Password
import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserInformation

data class UserBuilder(
    val information: UserInformation = UserInformationBuilder().build(),
    val password: Password = PasswordBuilder().build(),
    val loginPolicy: LoginPolicy = LoginPolicyBuilder().build(),
    val loginType: LoginType = LoginType.SIMPLE,
    val role: UserRole = UserRole.USER,
    val id: Long = 0
) {
    fun build(): User {
        return User(
            information = information,
            password = password,
            loginPolicy = loginPolicy,
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
    fun build(): UserInformation {
        return UserInformation(
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

data class LoginPolicyBuilder(
    val failedPasswordCount: Int = 0
) {
    fun build(): LoginPolicy {
        return LoginPolicy(
            failedPasswordCount = failedPasswordCount
        )
    }
}

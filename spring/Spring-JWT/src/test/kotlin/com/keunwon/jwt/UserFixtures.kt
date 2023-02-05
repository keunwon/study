package com.keunwon.jwt

import com.keunwon.jwt.domain.LoginType
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

const val USERNAME = "test-id"
const val USER_EMAIL = "test@test.com"
const val USER_PASSWORD = "password"
const val USER_WRONG_PASSWORD = "failure"
const val USER_FULL_NAME = "홍길동"
const val USER_NICKNAME = "닉네임"
const val USER_ACCOUNT_LOCKED = false
val USER_ROLE = UserRole.USER

fun createUser(
    username: String = USERNAME,
    password: String = USER_PASSWORD,
    name: String = USER_FULL_NAME,
    nickname: String = USER_NICKNAME,
    loginType: LoginType = LoginType.SIMPLE,
    isAccountNonLocked: Boolean = true,
    role: UserRole = USER_ROLE,
    failedPasswordCount: Int = 0,
    id: Long = 0,
): User {
    return User(
        username = username,
        password = password,
        name = name,
        nickname = nickname,
        loginType = loginType,
        role = role,
        isAccountNonLocked = isAccountNonLocked,
        failedPasswordCount = failedPasswordCount,
        id = id,
    )
}

class InmemoryUserRepository : InmemoryRepository<User>(), UserRepository {
    override fun findById(id: Long): Optional<User> = super.findById(id)

    override fun deleteById(id: Long) = super.deleteById(id)

    override fun existsById(id: Long): Boolean = super.existsById(id)

    override fun findByUsername(username: String): User? = findAll().find { it.username == username }

    override fun findByEmail(email: String): User? = findAll().find { it.email == email }

    override fun existsByUsername(username: String): Boolean = findAll().any { it.username == username }

    override fun existsByEmail(email: String): Boolean = findAll().any { it.email == email }
}

object TestPasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        return rawPassword.toString()
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return rawPassword == encodedPassword
    }
}

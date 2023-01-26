package com.keunwon.jwt.api

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.NotBlank

data class UserSignDto(
    @field:NotBlank(message = "사용자 아이디를 입력해주세요")
    val username: String,

    @field:NotBlank(message = "사용자 패스워드를 입력해주세요")
    val password: String,

    @field:NotBlank(message = "사용자 이름을 입력해주세요")
    val name: String,

    @field:NotBlank(message = "닉네임을 입력해주세요")
    val nickname: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder) = User(
        name = name,
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
    constructor(user: User) : this(user.id, user.username, user.nickname)
}

package com.keunwon.jwt.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class UserSignDto(
    @field:NotBlank(message = "{required.message}")
    val username: String,

    @field:NotBlank(message = "{required.message}")
    val password: String,

    @field:NotBlank(message = "{required.message}")
    val name: String,

    @field:NotBlank(message = "{required.message}")
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

data class UserSignResponse(
    val id: Long,
    val username: String,
    val nickname: String,
) {
    constructor(user: User) : this(user.id, user.username!!, user.nickname)
}

data class AccessTokenIssue(
    @field:NotBlank(message = "{required.message}")
    val username: String,

    @field:NotBlank(message = "{required.message}")
    val refreshToken: String,
)

data class AccessToken(
    val accessToken: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expirationAccessToken: LocalDateTime,
)

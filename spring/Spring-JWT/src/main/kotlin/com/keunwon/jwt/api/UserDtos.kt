package com.keunwon.jwt.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserRole
import com.keunwon.jwt.security.jwt.JwtAccessToken
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class UserSignRequest(
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
        isAccountNonLocked = true,
        role = UserRole.USER,
    )
}

data class AccessTokenIssueRequest(
    @field:NotBlank(message = "{required.message}")
    val username: String,

    @field:NotBlank(message = "{required.message}")
    val refreshToken: String,
)

data class AccessToken(
    val value: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expirationAccessToken: LocalDateTime,
) {
    constructor(jwtAccessToken: JwtAccessToken) : this(
        jwtAccessToken.value,
        jwtAccessToken.expiredAt.toLocalDateTime()
    )
}

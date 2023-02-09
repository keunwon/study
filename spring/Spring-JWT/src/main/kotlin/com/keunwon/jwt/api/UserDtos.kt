package com.keunwon.jwt.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.keunwon.jwt.common.REQUIRED_MESSAGE
import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.common.util.toLocalDateTime
import com.keunwon.jwt.domain.user.LoginType
import com.keunwon.jwt.domain.user.Password
import com.keunwon.jwt.domain.user.User
import com.keunwon.jwt.domain.user.UserInformation
import com.keunwon.jwt.security.jwt.JwtAccessToken
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class UserSignRequest(
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val confirmPassword: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val nickname: String,

    @field:NotBlank
    val authenticationCode: String,
) {
    fun toEntity(passwordEncoder: PasswordEncoder): User {
        return User(
            UserInformation(email, name, nickname),
            Password(password),
            loginType = LoginType.SIMPLE,
            role = UserRole.USER,
        )
    }
}

data class AccessTokenIssueRequest(
    @field:NotBlank(message = REQUIRED_MESSAGE)
    val email: String,

    @field:NotBlank(message = REQUIRED_MESSAGE)
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

data class EditPasswordRequest(
    @field:NotBlank
    val oldPassword: String,

    @field:NotBlank
    val newPassword: String,
)

data class EditUserPassword(
    val id: Long,
    val oldPassword: Password,
    val newPassword: Password,
) {
    constructor(id: Long, request: EditPasswordRequest) : this(
        id,
        Password(request.oldPassword),
        Password(request.newPassword),
    )
}

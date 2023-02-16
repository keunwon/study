package com.github.keunwon.appauth.api

import com.github.keunwon.user.service.UserRegister
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
    fun toRegister(): UserRegister {
        return UserRegister(
            email,
            name,
            nickname,
            password,
            confirmPassword,
            authenticationCode,
        )
    }
}

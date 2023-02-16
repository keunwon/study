package com.github.keunwon.appauth.api

import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.service.PasswordEdit
import javax.validation.constraints.NotBlank

data class EditPasswordRequest(
    @field:NotBlank
    val oldPassword: String,

    @field:NotBlank
    val newPassword: String,
) {
    fun toPasswordEdit(email: String): PasswordEdit {
        return PasswordEdit(
            email,
            Password(oldPassword),
            Password(newPassword),
        )
    }
}

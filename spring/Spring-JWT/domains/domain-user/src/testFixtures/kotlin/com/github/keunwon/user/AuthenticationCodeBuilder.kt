package com.github.keunwon.user

import com.github.keunwon.user.authenticationcode.AuthenticationCode
import java.time.LocalDateTime

const val AUTHENTICATION_CODE_EMAIL = "test@test.com"
const val AUTHENTICATION_VALID_CODE_CODE = "VALID_CODE"
const val AUTHENTICATION_WRONG_CODE_CODE = "WRONG_CODE"

data class AuthenticationCodeBuilder(
    val email: String = AUTHENTICATION_CODE_EMAIL,
    val code: String = AUTHENTICATION_VALID_CODE_CODE,
    val authenticated: Boolean = false,
    val createLocalDate: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0
) {
    fun build(): AuthenticationCode {
        return AuthenticationCode(
            email = email,
            code = code,
            authenticated = authenticated,
            createLocalDate = createLocalDate,
            id = id
        )
    }
}

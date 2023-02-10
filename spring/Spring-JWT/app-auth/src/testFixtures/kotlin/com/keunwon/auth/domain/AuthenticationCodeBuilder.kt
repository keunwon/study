package com.keunwon.auth.domain

import com.keunwon.auth.domain.authenticationcode.AuthenticationCode
import java.time.LocalDateTime

const val AUTHENTICATION_EMAIL = "test@test.com"
const val VALID_CODE = "VALID"
const val INVALID_CODE = "INVALID"

data class AuthenticationCodeBuilder(
    val email: String = AUTHENTICATION_EMAIL,
    val code: String = VALID_CODE,
    val authenticated: Boolean = false,
    val createAt: LocalDateTime = LocalDateTime.now(),
    val id: Long = 0L,
) {
    fun build(): AuthenticationCode {
        return AuthenticationCode(
            email = email,
            code = code,
            authenticated = authenticated,
            createdAt = createAt,
            id = id,
        )
    }
}

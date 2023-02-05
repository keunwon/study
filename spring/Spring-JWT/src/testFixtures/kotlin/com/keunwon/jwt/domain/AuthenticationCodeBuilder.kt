package com.keunwon.jwt.domain

const val AUTHENTICATION_EMAIL = "test@test.com"
const val VALID_CODE = "VALID"
const val INVALID_CODE = "INVALID"

data class AuthenticationCodeBuilder(
    val email: String = AUTHENTICATION_EMAIL,
    val code: String = VALID_CODE,
    val authenticated: Boolean = false,
) {
    fun build(): AuthenticationCode {
        return AuthenticationCode(
            email = email,
            code = code,
            authenticated = authenticated
        )
    }
}

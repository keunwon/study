package com.keunwon.jwt

import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.generatedGrantedAuthorityList
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

const val LOGIN_USERNAME = "test-id"
const val LOGIN_PASSWORD = "password"

val AUTHENTICATION_USER = createUser()
val AUTHENTICATION_ROLES = generatedGrantedAuthorityList(AUTHENTICATION_USER.role)

fun createPreAuthenticationToken(
    username: String = LOGIN_USERNAME,
    password: String = LOGIN_PASSWORD,
): UsernamePasswordAuthenticationToken {
    return UsernamePasswordAuthenticationToken(username, password)
}

object JwtAuthenticationManagerStub : AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        return createPostAuthenticationToken()
    }
}

fun createPostAuthenticationToken(user: User = AUTHENTICATION_USER): UsernamePasswordAuthenticationToken {
    return UsernamePasswordAuthenticationToken.authenticated(user, "", AUTHENTICATION_ROLES)
}

package com.keunwon.jwt

import com.keunwon.jwt.common.mapGrantedAuthority
import com.keunwon.jwt.domain.UserBuilder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder

object TestPasswordEncoder : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {
        return rawPassword.toString()
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return rawPassword == encodedPassword
    }
}

const val LOGIN_USERNAME = "test@test.com"
const val LOGIN_PASSWORD = "PASSWORD"

fun createUsernamePasswordAuthenticationToken(
    username: String = LOGIN_USERNAME,
    password: String = LOGIN_PASSWORD,
): UsernamePasswordAuthenticationToken {
    return UsernamePasswordAuthenticationToken(username, password)
}

val AUTHENTICATION_USER = UserBuilder().build()
val AUTHENTICATION_ROLES = mapGrantedAuthority(AUTHENTICATION_USER.role)

object JwtAuthenticationManagerStub : AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication {
        return UsernamePasswordAuthenticationToken
            .authenticated(AUTHENTICATION_USER, "", AUTHENTICATION_ROLES)
    }
}

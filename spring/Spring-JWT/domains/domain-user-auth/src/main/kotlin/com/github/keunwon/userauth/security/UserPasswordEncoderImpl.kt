package com.github.keunwon.userauth.security

import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.UserPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class UserPasswordEncoderImpl(private val passwordEncoder: PasswordEncoder) : UserPasswordEncoder {
    override fun encrypt(rawPassword: Password): Password {
        val encoder = passwordEncoder.encode(rawPassword.value)
        return Password(encoder)
    }

    override fun matches(rawPassword: Password, encryptPassword: Password): Boolean {
        return passwordEncoder.matches(rawPassword.value, encryptPassword.value)
    }
}

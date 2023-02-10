package com.keunwon.auth.domain.user

import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Password(
    @Column(name = "password", length = 255)
    val value: String,
)

fun PasswordEncoder.matches(rawPassword: Password, encodedPassword: Password): Boolean {
    return matches(rawPassword.value, encodedPassword.value)
}

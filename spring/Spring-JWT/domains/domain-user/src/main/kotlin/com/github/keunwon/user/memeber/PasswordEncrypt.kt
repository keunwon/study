package com.github.keunwon.user.memeber

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

interface PasswordEncrypt {
    fun encrypt(rawPassword: Password): Password

    fun matches(rawPassword: Password, encryptPassword: Password): Boolean
}

@ConditionalOnMissingBean
class NoPasswordEncrypt : PasswordEncrypt {
    override fun encrypt(rawPassword: Password): Password {
        return Password(rawPassword.value)
    }

    override fun matches(rawPassword: Password, encryptPassword: Password): Boolean {
        return rawPassword == encryptPassword
    }
}

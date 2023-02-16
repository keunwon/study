package com.github.keunwon.user.memeber

interface UserPasswordEncoder {
    fun encrypt(rawPassword: Password): Password

    fun matches(rawPassword: Password, encryptPassword: Password): Boolean
}

//@ConditionalOnMissingBean
class UserPasswordNoEncoder : UserPasswordEncoder {
    override fun encrypt(rawPassword: Password): Password {
        return Password(rawPassword.value)
    }

    override fun matches(rawPassword: Password, encryptPassword: Password): Boolean {
        return rawPassword == encryptPassword
    }
}

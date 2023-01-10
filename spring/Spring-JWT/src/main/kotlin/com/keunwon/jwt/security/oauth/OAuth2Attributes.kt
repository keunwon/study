package com.keunwon.jwt.security.oauth

import com.keunwon.jwt.domain.LoginType
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole

abstract class OAuth2Attributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String
) {
    open val name: String = getAttributeValue("name")
    open val email: String = getAttributeValue("email")
    open val nickname: String = getAttributeValue("nickname")

    abstract fun toEntity(): User

    fun getAttributeValue(key: String): String {
        val map = attributes[nameAttributeKey] as Map<*, *>
        return map[key] as String
    }
}

class NaverOAuth2Attributes(
    attributes: Map<String, Any>,
    nameAttributeKey: String,
) : OAuth2Attributes(attributes, nameAttributeKey) {
    val id = getAttributeValue("id")
    val gender = getAttributeValue("gender")
    val birthday = getAttributeValue("birthday")
    val birthyear = getAttributeValue("birthyear")

    override fun toEntity(): User = User(
        name = name,
        email = email,
        nickname = nickname,
        loginType = LoginType.OAUTH,
        role = UserRole.USER,
    )
}

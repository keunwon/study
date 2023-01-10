package com.keunwon.jwt.security.oauth

import com.keunwon.jwt.domain.User

abstract class OAuth2Attributes(val attributes: Map<String, Any>, val nameAttributeKey: String) {
    open val name: String
        get() = this.getValue("name")

    open val nickname: String
        get() = this.getValue("nickname")

    open val email: String
        get() = this.getValue("email")

    abstract fun userProfileAttributes(): Map<String, String>

    fun toEntity(): User = User.ofOAuth2(name, nickname, email)

    fun getValue(key: String): String = userProfileAttributes()[key] as String
}

class NaverOAuth2Attributes(
    attributes: Map<String, Any>,
    nameAttributeKey: String,
) : OAuth2Attributes(attributes, nameAttributeKey) {
    val id = getValue("id")
    val gender = getValue("gender")
    val birthday = getValue("birthday")
    val birthyear = getValue("birthyear")

    @Suppress("UNCHECKED_CAST")
    override fun userProfileAttributes(): Map<String, String> = attributes[nameAttributeKey] as Map<String, String>
}

class GoogleOAuth2Attributes(
    attributes: Map<String, Any>,
    nameAttributeKey: String,
) : OAuth2Attributes(attributes, nameAttributeKey) {
    val sub = getValue("sub")
    val picture = getValue("picture")
    val locale = getValue("locale")

    override val nickname = getValue("name")

    @Suppress("UNCHECKED_CAST")
    override fun userProfileAttributes(): Map<String, String> = attributes as Map<String, String>
}

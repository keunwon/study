package com.keunwon.jwt.security.oauth

import com.keunwon.jwt.domain.LoginType
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRole
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User

abstract class OAuth2Attributes(val attributes: Map<String, Any>, val nameAttributeKey: String) {
    abstract val name: String
    abstract val nickname: String
    abstract val email: String

    abstract fun userProfileAttributes(): Map<String, String>

    fun toEntity(): User = User(
        name = this.name,
        nickname = this.nickname,
        email = this.email,
        loginType = LoginType.OAUTH,
        role = UserRole.USER,
    )

    fun getValue(key: String): String = userProfileAttributes()[key] as String
}

object OAuth2AttributeFactory {
    fun create(userRequest: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2Attributes {
        val registrationId = userRequest.clientRegistration.registrationId
        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        return when (registrationId.uppercase()) {
            "NAVER" -> NaverOAuth2Attributes(oAuth2User.attributes, userNameAttributeName)
            "GOOGLE" -> GoogleOAuth2Attributes(oAuth2User.attributes, userNameAttributeName)
            else -> throw IllegalArgumentException("지원하지 않습니다. id=$registrationId")
        }
    }
}

class NaverOAuth2Attributes(
    attributes: Map<String, Any>,
    nameAttributeKey: String,
) : OAuth2Attributes(attributes, nameAttributeKey) {
    override val name: String = getValue("name")
    override val nickname: String = getValue("nickname")
    override val email: String = getValue("email")

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
    override val name: String = getValue("name")
    override val email: String = getValue("email")
    override val nickname = getValue("name")

    val sub = getValue("sub")
    val picture = getValue("picture")
    val locale = getValue("locale")

    @Suppress("UNCHECKED_CAST")
    override fun userProfileAttributes(): Map<String, String> = attributes as Map<String, String>
}

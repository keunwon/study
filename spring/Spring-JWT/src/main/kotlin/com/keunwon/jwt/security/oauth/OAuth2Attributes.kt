package com.keunwon.jwt.security.oauth

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.common.UserRole
import com.keunwon.jwt.domain.user.LoginType
import com.keunwon.jwt.domain.user.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User

sealed class Oauth2Attributes {
    abstract val email: String
    abstract val name: String
    abstract val nickname: String

    abstract fun toOAuth2User(role: String, oAuth2User: OAuth2User): OAuth2User

    fun toUserEntity() = User(
        name = name,
        nickname = nickname,
        email = email,
        loginType = LoginType.OAUTH2,
        role = UserRole.USER,
    )
}

data class GoogleOAuth2Attributes(
    override val email: String,
    override val name: String,
    override val nickname: String = email,
    val sub: String,
    val picture: String,
    val locale: String,
) : Oauth2Attributes() {
    override fun toOAuth2User(role: String, oAuth2User: OAuth2User): OAuth2User {
        return DefaultOAuth2User(listOf(SimpleGrantedAuthority(role)), oAuth2User.attributes, "email")
    }
}

data class NaverOAuth2Attributes(
    override val email: String,
    override val name: String,
    override val nickname: String,
) : Oauth2Attributes() {
    override fun toOAuth2User(role: String, oAuth2User: OAuth2User): OAuth2User {
        return DefaultOAuth2User(listOf(SimpleGrantedAuthority(role)), oAuth2User.getAttribute("response"), "email")
    }
}

object OAuth2AttributeFactory {
    private val objectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun create(
        oAuth2UserRequest: OAuth2UserRequest,
        oAuth2User: OAuth2User,
    ): Oauth2Attributes {
        return when (oAuth2UserRequest.registrationId().uppercase()) {
            "GOOGLE" -> objectMapper.convertValue(oAuth2User.attributes, GoogleOAuth2Attributes::class.java)
            "NAVER" -> objectMapper.convertValue(
                oAuth2User.getAttribute(oAuth2UserRequest.userNameAttributeName()), NaverOAuth2Attributes::class.java
            )
            else -> throw IllegalArgumentException("")
        }
    }
}

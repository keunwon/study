package com.keunwon.jwt.security.oauth

import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2UserService(private val userRepository: UserRepository) :
    OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = defaultOAuth2UserService.loadUser(userRequest)
        val oAuth2Attributes = OAuth2AttributeFactory.create(userRequest, oAuth2User)
        val user = saveOrUpdate(oAuth2Attributes)
        return oAuth2Attributes.toOAuth2User(user.role.name, oAuth2User)
    }

    private fun saveOrUpdate(oAuth2Attributes: Oauth2Attributes): User {
        val user = userRepository.findByEmail(oAuth2Attributes.email)?.apply { oAuthLogin(oAuth2Attributes) }
            ?: oAuth2Attributes.toUserEntity()
        return userRepository.save(user)
    }

    private fun User.oAuthLogin(oAuth2Attributes: Oauth2Attributes) {
        this.email = oAuth2Attributes.email
        this.name = oAuth2Attributes.name
        this.nickname = oAuth2Attributes.nickname
    }

    companion object : LogSupport {
        private val defaultOAuth2UserService = DefaultOAuth2UserService()
    }
}

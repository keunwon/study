package com.keunwon.jwt.security.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.security.jwt.JwtProvider
import com.keunwon.jwt.security.jwt.TokenIssue
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomOAuth2UserService(private val userRepository: UserRepository)
    : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = defaultOAuth2UserService.loadUser(userRequest)
        val user = saveOrUpdate(oAuthAttributesFactory(userRequest, oAuth2User))
        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority(user.role.name)),
            oAuth2User.attributes,
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        )
    }

    private fun saveOrUpdate(oAuth2Attributes: OAuth2Attributes): User {
        val user = userRepository.findByEmail(oAuth2Attributes.email)
            ?.apply { this.updateByOauthLogin(oAuth2Attributes) }
            ?: oAuth2Attributes.toEntity()
        return userRepository.save(user)
    }

    fun oAuthAttributesFactory(
        userRequest: OAuth2UserRequest,
        oAuth2User: OAuth2User,
    ): OAuth2Attributes {
        val registrationId = userRequest.clientRegistration.registrationId
        val nameAttributeKey = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        return when (registrationId) {
            "naver" -> NaverOAuth2Attributes(oAuth2User.attributes, nameAttributeKey)
            else -> throw IllegalArgumentException("지원하지 않습니다. registrationId: $registrationId")
        }
    }

    companion object : LogSupport {
        private val defaultOAuth2UserService = DefaultOAuth2UserService()
    }
}

@Component
class OAuthAuthenticationSuccessHandler(
    private val objectMapper: ObjectMapper,
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
) : AuthenticationSuccessHandler {

    @Transactional
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val userToken = jwtProvider.createTokenIssue(authentication)
        val oAuth2User = authentication.principal as OAuth2User
        saveOrUpdateUserToken(userToken, oAuth2User)

        response.apply {
            status = HttpStatus.OK.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            objectMapper.writeValue(outputStream, userToken)
        }
    }

    fun saveOrUpdateUserToken(token: TokenIssue, oAuth2User: OAuth2User) {
        val attributes = getAttributes(oAuth2User)
        val email = attributes.getValue("email")
        val user = userRepository.findByEmail(email)!!
        user.successLogin(token.toEntity(user.id!!))
        userRepository.save(user)
    }

    private fun getAttributes(oAuth2User: OAuth2User): Map<String, String> {
        return oAuth2User.name.run { substring(1, length - 1) }
            .split(", ")
            .associate {
                val (key, value) = it.split("=")
                key to value
            }
    }
}

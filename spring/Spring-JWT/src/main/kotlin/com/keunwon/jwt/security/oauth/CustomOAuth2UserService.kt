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
import org.springframework.transaction.annotation.Transactional
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class CustomOAuth2UserService(private val userRepository: UserRepository) :
    OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = defaultOAuth2UserService.loadUser(userRequest)
        val oAuth2Attributes = OAuth2AttributeFactory.create(userRequest, oAuth2User)
        val user = saveOrUpdate(oAuth2Attributes)
        return DefaultOAuth2User(
            listOf(SimpleGrantedAuthority(user.role.name)),
            oAuth2Attributes.userProfileAttributes(),
            "email"
        )
    }

    private fun saveOrUpdate(oAuth2Attributes: OAuth2Attributes): User {
        val user = userRepository.findByEmail(oAuth2Attributes.email)
            ?.apply { oAuthLogin(oAuth2Attributes) }
            ?: oAuth2Attributes.toEntity()
        return userRepository.save(user)
    }

    private fun User.oAuthLogin(oAuth2Attributes: OAuth2Attributes) {
        this.email = oAuth2Attributes.email
        this.name = oAuth2Attributes.name
        this.nickname = oAuth2Attributes.nickname
    }

    companion object : LogSupport {
        private val defaultOAuth2UserService = DefaultOAuth2UserService()
    }
}

open class OAuthAuthenticationSuccessHandler(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
    private val objectMapper: ObjectMapper,
) : AuthenticationSuccessHandler {

    @Transactional
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
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

    private fun saveOrUpdateUserToken(token: TokenIssue, oAuth2User: OAuth2User) {
        val user = userRepository.findByEmail(oAuth2User.name)!!
            .apply { successLogin(token.toEntity(id)) }
        userRepository.save(user)
    }
}

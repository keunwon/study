package com.keunwon.jwt.security.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.domain.user.UserRepository
import com.keunwon.jwt.domain.usertoken.UserTokenRepository
import com.keunwon.jwt.security.jwt.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class OAuthConfiguration(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
    private val jwtProvider: JwtProvider,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun customOAuth2UserService(): OAuth2UserService<OAuth2UserRequest, OAuth2User> =
        CustomOAuth2UserService(userRepository)

    @Bean
    fun oAuthAuthenticationSuccessHandler(): AuthenticationSuccessHandler =
        OAuthAuthenticationSuccessHandler(jwtProvider, userRepository, userTokenRepository, objectMapper)

    @Bean
    fun oAuthAuthenticationFailureHandler(): AuthenticationFailureHandler =
        OAuthAuthenticationFailureHandler(objectMapper)
}

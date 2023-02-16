package com.github.keunwon.userauth.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.keunwon.user.memeber.UserPasswordEncoder
import com.github.keunwon.user.service.UserAuthenticationService
import com.github.keunwon.userauth.usertoken.UserTokenService
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
class JwtAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userPasswordEncoder(passwordEncoder: PasswordEncoder): UserPasswordEncoder {
        return UserPasswordEncoderImpl(passwordEncoder)
    }

    @Bean
    fun jwtAuthenticationManager(
        userAuthenticationService: UserAuthenticationService,
    ): JwtAuthenticationManager {
        return JwtAuthenticationManager(userAuthenticationService)
    }

    @Bean
    fun jwtLoginAuthenticationSuccessHandler(
        userTokenService: UserTokenService,
        objectMapper: ObjectMapper,
    ): AuthenticationSuccessHandler {
        return JwtLoginAuthenticationSuccessHandler(userTokenService, objectMapper)
    }

    @Bean
    fun jwtLoginAuthenticationFailureHandler(
        objectMapper: ObjectMapper,
    ): AuthenticationFailureHandler {
        return JwtLoginAuthenticationFailureHandler(objectMapper)
    }
}

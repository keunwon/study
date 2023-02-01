package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.domain.UserRepository
import com.keunwon.jwt.domain.UserTokenRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

@Configuration
class JwtConfiguration(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
    private val jwtProvider: JwtProvider,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun jwtAuthenticationManager(): AuthenticationManager = JwtAuthenticationManager(userRepository, passwordEncoder())

    @Bean
    fun jwtLoginAuthenticationSuccessHandler(): AuthenticationSuccessHandler =
        JwtLoginAuthenticationSuccessHandler(jwtProvider, userTokenRepository, objectMapper)

    @Bean
    fun jwtLoginAuthenticationFailureHandler(): AuthenticationFailureHandler =
        JwtLoginAuthenticationFailureHandler(objectMapper)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

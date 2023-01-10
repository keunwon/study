package com.keunwon.jwt.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.config.auth.JwtUserDetailsServiceImpl
import com.keunwon.jwt.domain.User
import com.keunwon.jwt.domain.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class JwtConfiguration {

    @Bean
    fun customUserDetailsServiceImpl(userRepository: UserRepository) =
        JwtUserDetailsServiceImpl(userRepository)

    @Bean
    fun jwtAuthenticationManager(
        jwtUserDetailsService: JwtUserDetailsService<User, Long>,
        passwordEncoder: PasswordEncoder,
    ) : JwtAuthenticationManager = JwtAuthenticationManager(jwtUserDetailsService, passwordEncoder)

    @Bean
    fun customAuthenticationSuccessHandler(
        jwtProvider: JwtProvider,
        customUserDetailsServiceImpl: JwtUserDetailsServiceImpl,
        objectMapper: ObjectMapper,
    ): JwtLoginAuthenticationSuccessHandler =
        JwtLoginAuthenticationSuccessHandler(jwtProvider, customUserDetailsServiceImpl, objectMapper)

    @Bean
    fun customAuthenticationFailureHandler(
        customUserDetailsServiceImpl: JwtUserDetailsServiceImpl, objectMapper: ObjectMapper) =
        JwtLoginAuthenticationFailureHandler(objectMapper)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

package com.keunwon.jwt.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.config.CustomUserDetailsServiceImpl
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
        CustomUserDetailsServiceImpl(userRepository)

    @Bean
    fun jwtAuthenticationManager(
        customUserDetailsService: CustomUserDetailsService<User, Long>,
        passwordEncoder: PasswordEncoder,
    ) : JwtAuthenticationManager = JwtAuthenticationManager(customUserDetailsService, passwordEncoder)

    @Bean
    fun customAuthenticationFilter(
        jwtAuthenticationManager: JwtAuthenticationManager,
        customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
        customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    ): CustomAuthenticationFilter = CustomAuthenticationFilter(jwtAuthenticationManager).apply {
        setAuthenticationSuccessHandler(customAuthenticationSuccessHandler)
        setAuthenticationFailureHandler(customAuthenticationFailureHandler)
    }

    @Bean
    fun customAuthenticationSuccessHandler(
        tokenProvider: TokenProvider,
        customUserDetailsServiceImpl: CustomUserDetailsServiceImpl,
    ): CustomAuthenticationSuccessHandler = CustomAuthenticationSuccessHandler(tokenProvider, customUserDetailsServiceImpl)

    @Bean
    fun customAuthenticationFailureHandler(customUserDetailsServiceImpl: CustomUserDetailsServiceImpl)=
        CustomAuthenticationFailureHandler(customUserDetailsServiceImpl)

    @Bean
    fun jwtAuthorizationFilter(jwtProvider: TokenProvider, objectMapper: ObjectMapper): JwtAuthorizationFilter =
        JwtAuthorizationFilter(jwtProvider, objectMapper)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

package com.keunwon.jwt.jwt

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
    fun customAuthenticationSuccessHandler(
        tokenProvider: TokenProvider,
        customUserDetailsServiceImpl: CustomUserDetailsServiceImpl,
    ): CustomAuthenticationSuccessHandler = CustomAuthenticationSuccessHandler(tokenProvider, customUserDetailsServiceImpl)

    @Bean
    fun customAuthenticationFailureHandler(customUserDetailsServiceImpl: CustomUserDetailsServiceImpl)=
        CustomAuthenticationFailureHandler(customUserDetailsServiceImpl)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

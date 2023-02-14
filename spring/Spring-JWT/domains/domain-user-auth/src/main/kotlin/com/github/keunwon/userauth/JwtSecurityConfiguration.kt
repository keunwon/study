package com.github.keunwon.userauth

import com.github.keunwon.user.service.UserAuthenticationService
import com.github.keunwon.userauth.security.JwtAuthenticationManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager

@Configuration
class JwtSecurityConfiguration(
    private val userAuthenticationService: UserAuthenticationService,
) {
//    @Bean
//    fun passwordEncrypt(): PasswordEncrypt {
//        return PasswordEn(BCryptPasswordEncoder())
//    }

    @Bean
    fun jwtAuthenticationManager(): AuthenticationManager {
        return JwtAuthenticationManager(userAuthenticationService)
    }
}

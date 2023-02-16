package com.github.keunwon.userauth

import com.github.keunwon.user.service.UserAuthenticationService
import org.springframework.context.annotation.Configuration

@Configuration
class JwtSecurityConfiguration(
    private val userAuthenticationService: UserAuthenticationService,
) {
//    @Bean
//    fun passwordEncrypt(): PasswordEncrypt {
//        return PasswordEn(BCryptPasswordEncoder())
//    }
}

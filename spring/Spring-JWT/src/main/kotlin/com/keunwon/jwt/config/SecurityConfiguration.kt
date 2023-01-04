package com.keunwon.jwt.config

import com.keunwon.jwt.jwt.CustomAuthenticationFilter
import com.keunwon.jwt.jwt.JwtAuthorizationFilter
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val customAuthenticationFilter: CustomAuthenticationFilter,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }

            headers {
                frameOptions {
                    sameOrigin = true
                    disable()
                }
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthorizationFilter)
            addFilterBefore<UsernamePasswordAuthenticationFilter>(customAuthenticationFilter)

            //securityMatcher("/api/**")
            authorizeHttpRequests {
                authorize(PathRequest.toH2Console(), permitAll)
                authorize("/resources/**", permitAll)
                authorize("/login", permitAll)
                authorize("/auth/sign", permitAll)

                authorize("/admin/**", hasRole("ADMIN"))
                authorize("/user/**", hasAnyRole("USER", "ADMIN"))
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }
}

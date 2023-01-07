package com.keunwon.jwt.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.jwt.CustomAuthenticationFailureHandler
import com.keunwon.jwt.jwt.CustomAuthenticationFilter
import com.keunwon.jwt.jwt.CustomAuthenticationSuccessHandler
import com.keunwon.jwt.jwt.JwtAuthenticationManager
import com.keunwon.jwt.jwt.JwtAuthorizationFilter
import com.keunwon.jwt.jwt.TokenProvider
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.HttpSecurityDsl
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class SecurityConfiguration {

    @Bean
    fun permitAllFilterChain(
        http: HttpSecurity,
        authenticationManager: JwtAuthenticationManager,
        customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
        customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    ): SecurityFilterChain {
        http {
            defaultSettings()

            securityMatcher("/resources/**")
            securityMatcher("/auth/sign", "/auth/login")
            securityMatcher(PathRequest.toH2Console())
            securityMatcher(PathRequest.toStaticResources().atCommonLocations())

            authorizeHttpRequests {
                authorize("/auth/login", permitAll)
                authorize(anyRequest, permitAll)
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                CustomAuthenticationFilter(authenticationManager).apply {
                    setAuthenticationSuccessHandler(customAuthenticationSuccessHandler)
                    setAuthenticationFailureHandler(customAuthenticationFailureHandler)
                })
        }
        return http.build()
    }

    @Bean
    fun jwtFilterChain(
        http: HttpSecurity,
        authenticationManager: JwtAuthenticationManager,
        tokenProvider: TokenProvider,
        objectMapper: ObjectMapper,
    ): SecurityFilterChain {
        http {
            defaultSettings()

            headers {
                frameOptions {
                    sameOrigin = true
                    disable()
                }
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthorizationFilter(tokenProvider, objectMapper))
            authorizeHttpRequests {
                authorize("/admin/**", hasRole("ADMIN"))
                authorize("/user/**", hasAnyRole("USER", "ADMIN"))
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }

    fun HttpSecurityDsl.defaultSettings() {
        csrf { disable() }
        httpBasic { disable() }
        logout { disable() }
        anonymous { disable() }
        sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }

        headers {
            frameOptions {
                sameOrigin = true
                disable()
            }
        }
    }
}

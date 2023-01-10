package com.keunwon.jwt.config.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.security.jwt.JwtAuthenticationManager
import com.keunwon.jwt.security.jwt.JwtAuthorizationFilter
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationFailureHandler
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.jwt.security.jwt.JwtLoginAuthenticationSuccessHandler
import com.keunwon.jwt.security.jwt.JwtProvider
import com.keunwon.jwt.security.oauth.CustomOAuth2UserService
import com.keunwon.jwt.security.oauth.OAuthAuthenticationSuccessHandler
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
        jwtLoginAuthenticationSuccessHandler: JwtLoginAuthenticationSuccessHandler,
        jwtLoginAuthenticationFailureHandler: JwtLoginAuthenticationFailureHandler,
        oAuth2UserService: CustomOAuth2UserService,
        oAuthAuthenticationSuccessHandler: OAuthAuthenticationSuccessHandler,
    ): SecurityFilterChain {
        http {
            defaultSettings()

            securityMatcher("/resources/**")
            securityMatcher("/auth/sign", "/auth/login")
            securityMatcher("/oauth2/**", "/login/**")
            securityMatcher(PathRequest.toH2Console())
            securityMatcher(PathRequest.toStaticResources().atCommonLocations())

            authorizeHttpRequests {
                authorize("/auth/login", permitAll)
                authorize(anyRequest, permitAll)
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtLoginAuthenticationFilter(authenticationManager).apply {
                    setAuthenticationSuccessHandler(jwtLoginAuthenticationSuccessHandler)
                    setAuthenticationFailureHandler(jwtLoginAuthenticationFailureHandler)
                })

            oauth2Login {
                authenticationSuccessHandler = oAuthAuthenticationSuccessHandler
                userInfoEndpoint {
                    userService = oAuth2UserService
                }
            }
        }
        return http.build()
    }

    @Bean
    fun jwtFilterChain(
        http: HttpSecurity,
        authenticationManager: JwtAuthenticationManager,
        jwtProvider: JwtProvider,
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

            addFilterBefore<UsernamePasswordAuthenticationFilter>(JwtAuthorizationFilter(jwtProvider, objectMapper))
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

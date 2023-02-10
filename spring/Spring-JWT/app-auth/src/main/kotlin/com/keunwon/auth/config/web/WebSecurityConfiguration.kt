package com.keunwon.auth.config.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.auth.security.jwt.JwtAuthorizationFilter
import com.keunwon.auth.security.jwt.JwtConfiguration
import com.keunwon.auth.security.jwt.JwtLoginAuthenticationFilter
import com.keunwon.auth.security.jwt.JwtProvider
import com.keunwon.auth.security.oauth.OAuthConfiguration
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
class WebSecurityConfiguration(
    private val jwtConfiguration: JwtConfiguration,
    private val oAuthConfiguration: OAuthConfiguration,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun permitAllFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            defaultSettings()

            securityMatcher("/resources/**")
            securityMatcher(
                "/auth/authentication-code",
                "/auth/authenticate-email",
                "/auth/sign",
                "/auth/login",
            )
            securityMatcher("/oauth2/**", "/login/**")
            securityMatcher(PathRequest.toH2Console(), PathRequest.toStaticResources().atCommonLocations())

            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtLoginAuthenticationFilter(jwtConfiguration.jwtAuthenticationManager(), objectMapper).apply {
                    setAuthenticationSuccessHandler(jwtConfiguration.jwtLoginAuthenticationSuccessHandler())
                    setAuthenticationFailureHandler(jwtConfiguration.jwtLoginAuthenticationFailureHandler())
                })

            oauth2Login {
                authenticationFailureHandler = oAuthConfiguration.oAuthAuthenticationFailureHandler()
                authenticationSuccessHandler = oAuthConfiguration.oAuthAuthenticationSuccessHandler()
                userInfoEndpoint {
                    userService = oAuthConfiguration.customOAuth2UserService()
                }
            }
        }
        return http.build()
    }

    @Bean
    fun jwtFilterChain(http: HttpSecurity, jwtProvider: JwtProvider): SecurityFilterChain {
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

    private fun HttpSecurityDsl.defaultSettings() {
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

package com.github.keunwon.appauth.config.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.keunwon.userauth.jwt.JwtProvider
import com.github.keunwon.userauth.security.JwtAuthenticationManager
import com.github.keunwon.userauth.security.JwtAuthorizationFilter
import com.github.keunwon.userauth.security.JwtLoginAuthenticationFilter
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.HttpSecurityDsl
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
@Configuration
class WebSecurityConfiguration(
    private val jwtAuthenticationManager: JwtAuthenticationManager,
    private val jwtProvider: JwtProvider,
    private val jwtLoginAuthenticationSuccessHandler: AuthenticationSuccessHandler,
    private val jwtLoginAuthenticationFailureHandler: AuthenticationFailureHandler,
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun permitAllFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            initDisable()
            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtLoginAuthenticationFilter(
                    AntPathRequestMatcher("/auth/login", HttpMethod.POST.name),
                    jwtAuthenticationManager,
                    objectMapper,
                ).apply {
                    setAuthenticationSuccessHandler(jwtLoginAuthenticationSuccessHandler)
                    setAuthenticationFailureHandler(jwtLoginAuthenticationFailureHandler)
                }
            )
            securityMatcher(
                PathRequest.toH2Console(),
                PathRequest.toStaticResources().atCommonLocations(),
            )
            securityMatcher(
                "/auth/**",
                "/oauth2/**",
                "/login/**",
                "/resources/**",
            )
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
        }
        return http.build()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            initDisable()

            addFilterBefore<UsernamePasswordAuthenticationFilter>(
                JwtAuthorizationFilter(jwtProvider, objectMapper)
            )

            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }

    private fun HttpSecurityDsl.initDisable() {
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

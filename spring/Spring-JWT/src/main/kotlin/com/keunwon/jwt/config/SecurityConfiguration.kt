package com.keunwon.jwt.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.keunwon.jwt.jwt.CustomAuthenticationFilter
import com.keunwon.jwt.jwt.JwtAuthorizationFilter
import com.keunwon.jwt.jwt.TokenProvider
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

@EnableWebSecurity
@Configuration
class SecurityConfiguration(
    private val customAuthenticationFilter: CustomAuthenticationFilter,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
) {

    @Bean
    fun filterChain(http: HttpSecurity, jwtProvider: TokenProvider, objectMapper: ObjectMapper): SecurityFilterChain {
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

            authorizeHttpRequests {
                authorize(PathRequest.toH2Console(), permitAll)
                authorize(PathRequest.toStaticResources().atCommonLocations(), permitAll)
                skipRequestMatchers.forEach { authorize(it, permitAll) }

                authorize("/admin/**", hasRole("ADMIN"))
                authorize("/user/**", hasAnyRole("USER", "ADMIN"))
                authorize(anyRequest, authenticated)
            }
        }
        return http.build()
    }

    companion object {
        val skipRequestMatchers: List<RequestMatcher> = listOf(
            AntPathRequestMatcher(CustomAuthenticationFilter.LOGIN_URL),
            AntPathRequestMatcher("/auth/sign"),
            AntPathRequestMatcher("/resources/**"),
            AntPathRequestMatcher("/h2-console"),
        )

        fun matchSkipRequest(request: HttpServletRequest): Boolean = skipRequestMatchers.any { it.matches(request) }
    }
}

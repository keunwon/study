package com.github.keunwon.userauth.security

import com.github.keunwon.user.memeber.Password
import com.github.keunwon.user.memeber.User
import com.github.keunwon.user.service.UserAuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtAuthenticationManager(
    private val userAuthenticationService: UserAuthenticationService,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        return userAuthenticationService.authenticate(
            authentication.name,
            Password(authentication.credentials.toString()),
        ).getOrElse {
            throw InternalAuthenticationServiceException("사용자 로그인에 실패하였습니다. 사용자 명: ${authentication.name}", it)
        }.toAuthentication()
    }

    private fun User.toAuthentication(): Authentication {
        val roles = listOf(SimpleGrantedAuthority(role.name))
        return UsernamePasswordAuthenticationToken(this, "", roles)
    }
}

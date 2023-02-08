package com.keunwon.jwt.api

import com.keunwon.jwt.domain.authenticationcode.AuthenticationCodeRepository
import com.keunwon.jwt.domain.authenticationcode.getLastByEmail
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Profile("local")
@RestController
class LocalTestAuthenticationCodeApi(private val authenticationCodeRepository: AuthenticationCodeRepository) {
    @GetMapping("/auth/authentication-code")
    fun getAuthenticationCode(@RequestParam email: String): ResponseEntity<String> {
        val authenticationCode = authenticationCodeRepository.getLastByEmail(email)
        return ResponseEntity.ok(authenticationCode.code)
    }
}

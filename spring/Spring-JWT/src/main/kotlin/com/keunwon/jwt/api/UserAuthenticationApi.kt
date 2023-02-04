package com.keunwon.jwt.api

import com.keunwon.jwt.config.LogSupport
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthenticationApi(private val userAuthenticationService: UserAuthenticationService) {
    @PostMapping("/auth/sign")
    fun sing(@Validated @RequestBody request: UserSignRequest): ResponseEntity<Unit> {
        userAuthenticationService.sign(request)
        log.info("> ${request.username} 회원가입 완료")
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/auth/authentication-code")
    fun generateAuthenticationCode(@RequestParam email: String): ResponseEntity<Unit> {
        val code = userAuthenticationService.generateAuthenticationCode(email)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/auth/authenticate-email")
    fun authenticateEmail(@RequestParam email: String, @RequestParam code: String): ResponseEntity<Unit> {
        userAuthenticationService.authenticateEmail(email, code)
        return ResponseEntity.noContent().build()
    }

    companion object : LogSupport
}

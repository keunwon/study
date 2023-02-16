package com.github.keunwon.appauth.api

import com.github.keunwon.user.service.UserRegisterService
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthenticationApi(
    private val userRegisterService: UserRegisterService,
) {
    /**
     * 회원가입 API
     */
    @PostMapping("/auth/sign")
    fun sign(@Validated @RequestBody request: UserSignRequest): ResponseEntity<Unit> {
        userRegisterService.register(request.toRegister())
        log.info("> 회원가입 완료, email: ${request.email}")
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    /**
     * 사용자 인증 코드 생성 API
     */
    @PostMapping("/auth/authentication-code")
    fun generateAuthenticationCode(@RequestParam email: String): ResponseEntity<Unit> {
        val code = userRegisterService.generateAuthenticationCode(email)
        log.info("> 사용자 인증 코드 생성, 이메일: $email")
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    /**
     * 사용자 이메일 기준으로 인증코드 검증 API
     */
    @PostMapping("/auth/authenticate-email")
    fun authenticateEmail(
        @RequestParam email: String,
        @RequestParam code: String,
    ): ResponseEntity<Unit> {
        userRegisterService.authenticateEmail(email, code)
        log.info("> 사용자 인증코드 검증 완료, 이메일: $email")
        return ResponseEntity.noContent().build()
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}

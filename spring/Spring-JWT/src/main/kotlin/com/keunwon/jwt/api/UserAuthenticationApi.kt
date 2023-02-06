package com.keunwon.jwt.api

import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.security.jwt.JwtLoginUser
import com.keunwon.jwt.security.jwt.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAuthenticationApi(private val userAuthenticationService: UserAuthenticationService) {
    /**
     * 회원가입 API
     */
    @PostMapping("/auth/sign")
    fun sing(@Validated @RequestBody request: UserSignRequest): ResponseEntity<Unit> {
        userAuthenticationService.register(request)
        log.info("> ${request.username} 회원가입 완료")
        return ResponseEntity(HttpStatus.CREATED)
    }

    /**
     * 사용자 인증 코드 생성 API
     */
    @PostMapping("/auth/authentication-code")
    fun generateAuthenticationCode(@RequestParam email: String): ResponseEntity<Unit> {
        userAuthenticationService.generateAuthenticationCode(email)
        return ResponseEntity.noContent().build()
    }

    /**
     * 사용자 이메일을 기준으로 인증코드 검증 API
     */
    @PostMapping("/auth/authenticate-email")
    fun authenticateEmail(@RequestParam email: String, @RequestParam code: String): ResponseEntity<Unit> {
        userAuthenticationService.authenticateEmail(email, code)
        return ResponseEntity.noContent().build()
    }

    /**
     * 사용자 비밀번호 변경 API
     */
    @PostMapping("/auth/me/password")
    fun changePassword(
        @LoginUser jwtLoginUser: JwtLoginUser,
        @RequestParam password: String,
    ): ResponseEntity<Unit> {
        userAuthenticationService.changePassword(jwtLoginUser, password)
        return ResponseEntity.noContent().build()
    }

    companion object : LogSupport
}

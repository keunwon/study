package com.keunwon.auth.api

import com.keunwon.auth.config.LogSupport
import com.keunwon.auth.security.jwt.LoginUser
import com.keunwon.auth.security.jwt.LoginUserDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
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
        log.info("> 회원가입 완료, email: ${request.email}")
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    /**
     * 사용자 인증 코드 생성 API
     */
    @PostMapping("/auth/authentication-code")
    fun generateAuthenticationCode(@RequestParam email: String): ResponseEntity<Unit> {
        val code = userAuthenticationService.generateAuthenticationCode(email)
        log.info("> code: $code")
        return ResponseEntity.noContent().build()
    }

    /**
     * 사용자 이메일을 기준으로 인증코드 검증 API
     */
    @PostMapping("/auth/authenticate-email")
    fun authenticateEmail(
        @RequestParam email: String,
        @RequestParam code: String,
    ): ResponseEntity<Unit> {
        userAuthenticationService.authenticateEmail(email, code)
        return ResponseEntity.noContent().build()
    }

    /**
     * 사용자 비밀번호 변경 API
     */
    @PatchMapping("/auth/me/edit-password")
    fun changePassword(
        @RequestBody request: EditPasswordRequest,
        @LoginUser loginUserDto: LoginUserDto,
    ): ResponseEntity<Unit> {
        userAuthenticationService.changePassword(EditUserPassword(loginUserDto.id, request))
        log.info("> 비밀번호 변경 완료, 사용자 이메일: ${loginUserDto.email}")
        return ResponseEntity.noContent().build()
    }

    companion object : LogSupport
}

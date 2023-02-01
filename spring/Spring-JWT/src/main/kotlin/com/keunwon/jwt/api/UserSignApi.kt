package com.keunwon.jwt.api

import com.keunwon.jwt.config.LogSupport
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSignApi(private val userService: UserService) {
    @PostMapping("/auth/sign")
    fun sing(@Validated @RequestBody request: UserSignRequest): ResponseEntity<Void> {
        userService.sign(request)
        log.info("> ${request.username} 회원가입 완료")
        return ResponseEntity.noContent().build()
    }

    companion object : LogSupport
}

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
    fun sing(@Validated @RequestBody userSignSignDto: UserSignDto): ResponseEntity<UserSignResponse> {
        return userService.sign(userSignSignDto).run {
            log.info("> 회원 가입 성공, 사용자명: $username")
            ResponseEntity.ok(UserSignResponse(this))
        }
    }

    companion object : LogSupport
}

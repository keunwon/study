package com.keunwon.jwt.api

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserSignApi(private val userService: UserService) {

    @PostMapping("/auth/sign")
    fun sing(@Validated @RequestBody userSignSignDto: UserSignDto): ResponseEntity<UserSignResponseDto> {
        val user = userService.sign(userSignSignDto)
        return ResponseEntity.ok(UserSignResponseDto(user))
    }
}

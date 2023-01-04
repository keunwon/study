package com.keunwon.jwt.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserApi(private val userService: UserService) {

    @PostMapping("/auth/sign")
    fun sing(@RequestBody userSignDto: UserDto): ResponseEntity<UserSignResponseDto> {
        val user = userService.sign(userSignDto)
        return ResponseEntity.ok(UserSignResponseDto(user))
    }

    @GetMapping("/auth/home")
    fun home(): ResponseEntity<String> = ResponseEntity.ok("ok")
}

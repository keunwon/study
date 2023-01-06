package com.keunwon.jwt.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserApi(private val userService: UserService) {

    @PostMapping("/auth/sign")
    fun sing(@Valid @RequestBody userSignSignDto: UserSignDto): ResponseEntity<UserSignResponseDto> {
        val user = userService.sign(userSignSignDto)
        return ResponseEntity.ok(UserSignResponseDto(user))
    }
}

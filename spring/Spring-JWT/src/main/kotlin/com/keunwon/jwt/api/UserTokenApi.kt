package com.keunwon.jwt.api

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserTokenApi(private val userTokenService: UserTokenService) {

    @PostMapping("/auth/refreshToken")
    fun refreshToken(@Validated @RequestBody dto: AccessTokenIssue): ResponseEntity<AccessToken> {
        return ResponseEntity.ok(userTokenService.refreshAccessToken(dto))
    }
}

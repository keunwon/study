package com.keunwon.jwt.api

import com.keunwon.jwt.config.LogSupport
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserTokenApi(private val userTokenService: UserTokenService) {

    @PostMapping("/auth/refreshToken")
    fun refreshToken(@Validated @RequestBody dto: AccessTokenIssue): ResponseEntity<AccessToken> {
        return userTokenService.refreshAccessToken(dto).run {
            log.info("> accessToken 재발급, 사용자명: ${dto.username}")
            ResponseEntity.ok(this)
        }
    }

    companion object : LogSupport
}

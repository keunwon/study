package com.keunwon.auth.api

import com.keunwon.auth.config.LogSupport
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserTokenApi(private val userTokenService: UserTokenService) {
    @PostMapping("/auth/refreshToken")
    fun refreshToken(@Validated @RequestBody request: AccessTokenIssueRequest): ResponseEntity<AccessToken> {
        val response = userTokenService.refreshAccessToken(request)
        log.info("> ${request.email} accessToken 재발급")
        return ResponseEntity.ok(response)
    }

    companion object : LogSupport
}

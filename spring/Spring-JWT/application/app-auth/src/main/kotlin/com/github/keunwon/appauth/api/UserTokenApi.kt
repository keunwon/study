package com.github.keunwon.appauth.api

import com.github.keunwon.userauth.usertoken.UserTokenService
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserTokenApi(
    private val userTokenService: UserTokenService,
) {
    /**
     * RefreshToken 기준으로 AccessToken 재발급 API
     */
    @PostMapping("/auth/refreshToken")
    fun refreshToken(@Validated @RequestBody request: AccessTokenIssueRequest): ResponseEntity<AccessTokenResponse> {
        val accessToken = userTokenService.reissueAccessToken(request.toReissue())
        log.info("> accessToken 재발급 완료, email: ${request.email}")
        return ResponseEntity.ok(AccessTokenResponse(accessToken))
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}

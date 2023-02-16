package com.github.keunwon.appauth.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.github.keunwon.userauth.jwt.AccessToken
import com.github.keunwon.userauth.usertoken.Reissue
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class AccessTokenIssueRequest(
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val refreshToken: String,
) {
    fun toReissue(): Reissue {
        return Reissue(email, refreshToken)
    }
}

data class AccessTokenResponse(
    val value: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val expirationAccessToken: LocalDateTime,
) {
    constructor(accessToken: AccessToken) : this(
        accessToken.value,
        accessToken.expirationDateTime(),
    )
}


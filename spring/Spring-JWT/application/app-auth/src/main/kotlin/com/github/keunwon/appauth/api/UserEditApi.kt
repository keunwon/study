package com.github.keunwon.appauth.api

import com.github.keunwon.user.service.UserEditService
import com.github.keunwon.userauth.jwt.LoginUserDto
import com.github.keunwon.userauth.security.resolver.LoginUser
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserEditApi(
    private val userEditService: UserEditService,
) {
    /**
     * 사용자 비밀번호 변경 API
     */
    @PatchMapping("/api/me/edit-password")
    fun changePassword(
        @LoginUser loginUserDto: LoginUserDto,
        @Validated @RequestBody request: EditPasswordRequest,
    ): ResponseEntity<Unit> {
        userEditService.changePassword(request.toPasswordEdit(loginUserDto.email))
        log.info("> 사용자 비밀번호 변경 완료, email: ${loginUserDto.email}")
        return ResponseEntity.noContent().build()
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}

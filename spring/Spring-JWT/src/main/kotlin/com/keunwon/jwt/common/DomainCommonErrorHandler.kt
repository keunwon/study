package com.keunwon.jwt.common

import com.keunwon.jwt.security.jwt.JwtProvider
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DomainCommonErrorHandler {
    @ExceptionHandler(JwtException::class)
    fun handleExpiredJwtException(ex: ExpiredJwtException): ResponseEntity<ErrorDto> {
        val message = JwtProvider.validationErrorMessages[ex::class] ?: "토큰이 유효하지 않습니다"
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), message))
    }
}

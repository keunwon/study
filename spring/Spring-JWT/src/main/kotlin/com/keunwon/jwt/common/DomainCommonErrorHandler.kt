package com.keunwon.jwt.common

import com.keunwon.jwt.config.LogSupport
import com.keunwon.jwt.domain.user.UnIdentifiedUserException
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
    fun handleExpiredJwtException(exception: ExpiredJwtException): ResponseEntity<ErrorDto> {
        val message = JwtProvider.validationErrorMessages[exception::class] ?: exception.message
        log.warn("> 토큰이 유효하지 않습니다.", exception)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, message))
    }

    @ExceptionHandler(UnIdentifiedUserException::class)
    fun handleUnIdentifiedUserException(exception: UnIdentifiedUserException): ResponseEntity<ErrorDto> {
        log.error("> 사용자 인증을 실패하였습니다.", exception)
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorDto(HttpStatus.FORBIDDEN, exception.message))
    }

    companion object : LogSupport
}

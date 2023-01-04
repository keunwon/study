package com.keunwon.jwt.common

import com.keunwon.jwt.config.LogSupport
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val errorMessages = ex.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity.status(httpStatus)
            .body(ErrorDto(httpStatus.value(), "사용자가 요청한 파라미터 정보가 유효하지 않습니다.", errorMessages))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorDto> {
        log.error("> 알수 없는 오류 발생", ex)
        val httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(httpStatus).body(ErrorDto(httpStatus.value(), unknownErrorMessage))
    }

    fun createResponseEntity(status: HttpStatus, exception: Throwable): ResponseEntity<ErrorDto> {
        val message = exception.message ?: unknownErrorMessage
        val errorDto = ErrorDto(status.value(), message)
        return ResponseEntity.status(status).body(errorDto)
    }

    companion object : LogSupport {
        private const val unknownErrorMessage = "알수 없는 오류가 발생하였습니다."
    }
}

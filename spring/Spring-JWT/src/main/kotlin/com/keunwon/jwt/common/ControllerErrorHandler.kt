package com.keunwon.jwt.common

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.keunwon.jwt.config.LogSupport
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException

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

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorDto> {
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(ex: NoHandlerFoundException, request: WebRequest): ResponseEntity<ErrorDto> {
        val errorMessage = "url: ${ex.requestURL} 은 지원하지 않습니다.".also(log::warn)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorDto(HttpStatus.NOT_FOUND.value(), errorMessage))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorDto> {
        val errorMessage = when (val causeException = ex.cause) {
            is InvalidFormatException ->
                "${causeException.value}를 ${causeException.targetType}으로 변환 중 오류가 발생하였습니다."
            is MissingKotlinParameterException ->
                "${causeException.parameter.name} 파라미터가 존재하지 않습니다."
            else -> "올바르지 않은 요청 정보입니다."
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(ErrorDto(HttpStatus.BAD_REQUEST.value(), errorMessage))
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

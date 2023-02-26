package com.github.keunwon.appauth.common

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.github.keunwon.core.generic.ErrorDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import javax.servlet.ServletException

@ControllerAdvice
class SimpleErrorHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorDto> {
        log.error("> 관리자 확인이 필요합니다.", ex)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(createErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE))
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorDto> {
        log.error("> 관리자 확인이 필요합니다.", ex)
        return ResponseEntity
            .internalServerError()
            .body(createErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_MESSAGE))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorDto> {
        val errorMessage = ex.message ?: "사용자 요청 데이터를 확인해주세요."
        log.warn("> $errorMessage", ex)
        return ResponseEntity
            .badRequest()
            .body(createErrorDto(HttpStatus.BAD_REQUEST, ex.message!!))
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorDto> {
        val errorMessage = ex.message ?: "사용자가 사용할 수 없는 상태입니다."
        log.warn("> $errorMessage", ex)
        return ResponseEntity
            .internalServerError()
            .body(createErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val errorMessages = ex.fieldErrors.map { "${it.field} ${it.defaultMessage}" }
        log.warn("> $errorMessages", ex)
        return ResponseEntity
            .badRequest()
            .body(createErrorDto(HttpStatus.BAD_REQUEST, errorMessages.joinToString(",")))
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(ex: MissingServletRequestParameterException): ResponseEntity<ErrorDto> {
        log.warn("> 파라미터 값이 존재하지 않습니다. ", ex)
        val message = "${ex.message} 파라미터가 존재하지 않습니다."
        return ResponseEntity
            .badRequest()
            .body(createErrorDto(HttpStatus.BAD_REQUEST, message))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorDto> {
        val errorMessage = when (val causeException = ex.cause) {
            is InvalidFormatException ->
                "${causeException.value}를 ${causeException.targetType}으로 변환 중 오류가 발생하였습니다."
            is MissingKotlinParameterException ->
                "${causeException.parameter.name} 파라미터가 존재하지 않습니다."
            is JsonParseException -> "json 값이 유효하지 않습니다."
            else -> "요청 파라미터가 유효하지 않습니다."
        }
        log.warn("> $errorMessage", ex)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(createErrorDto(HttpStatus.BAD_REQUEST, errorMessage))
    }

    /**
     * Servlet Exception
     */
    @ExceptionHandler(ServletException::class)
    fun handleServletException(ex: ServletException): ResponseEntity<ErrorDto> {
        log.error("> servlet exception 발생", ex)
        return ResponseEntity
            .badRequest()
            .body(createErrorDto(HttpStatus.BAD_REQUEST, ex.message))
    }

    /**
     * 404 Not Found
     */
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(ex: NoHandlerFoundException, request: WebRequest): ResponseEntity<ErrorDto> {
        val errorMessage = "${ex.requestURL} 은 지원하지 않습니다."
        log.warn("> $errorMessage")
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(createErrorDto(HttpStatus.NOT_FOUND, errorMessage))
    }

    /**
     * 415 Unsupported Media Type
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleHttpMediaTypeNotSupportedException(ex: HttpMediaTypeNotSupportedException): ResponseEntity<ErrorDto> {
        val message = "지원하지 않는 Content-Type 입니다."
        log.warn("> $message, Request Content-Type: ${ex.contentType}")
        return ResponseEntity
            .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .body(createErrorDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message))
    }

    private fun createErrorDto(status: HttpStatus, message: String?): ErrorDto {
        return ErrorDto(status.value(), message)
    }

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
        private const val ERROR_MESSAGE = "알수 없는 오류가 발생하였습니다."
    }
}

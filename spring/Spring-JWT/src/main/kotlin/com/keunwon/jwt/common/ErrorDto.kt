package com.keunwon.jwt.common

import org.springframework.http.HttpStatus

data class ErrorDto(
    val code: Int,
    val message: String?,
    val errors: List<String> = mutableListOf()
) {
    constructor(httpStatus: HttpStatus, message: String?, errors: List<String> = emptyList()) : this(
        httpStatus.value(),
        message,
        errors,
    )
}

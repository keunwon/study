package com.keunwon.jwt.common

data class ErrorDto(
    val code: Int,
    val message: String,
    val errors: List<String> = mutableListOf()
)

package com.keunwon.jwt.common

data class ErrorDto(
    val status: Int,
    val message: String,
    val errors: List<String> = mutableListOf()
)

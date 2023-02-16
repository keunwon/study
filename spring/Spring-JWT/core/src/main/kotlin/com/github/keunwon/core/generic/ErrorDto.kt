package com.github.keunwon.core.generic

data class ErrorDto(val code: String, val message: String?) {
    constructor(code: Int, message: String?) : this(code.toString(), message)
}

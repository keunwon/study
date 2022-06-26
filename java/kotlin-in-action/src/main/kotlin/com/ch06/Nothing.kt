package com.ch06

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

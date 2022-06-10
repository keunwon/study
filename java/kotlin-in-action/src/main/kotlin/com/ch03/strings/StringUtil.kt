package com.ch03.strings

fun String.lastChar(): Char = this[length - 1]

val String.lastChar: Char
    get() = get(length -1 )

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) = this.setCharAt(length - 1, value)
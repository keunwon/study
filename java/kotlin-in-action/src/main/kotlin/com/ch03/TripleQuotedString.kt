package com.ch03

val kotlinLogo = """
    | //
    .| //
    .|/\""".trimIndent()

val num = 4
val test = """
    $44
    $num
    test
    """.trimIndent()

fun main() {
    println(kotlinLogo.trimMargin("."))
    println(test)
}
package com.ch02

fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!")
    println("${name}님 반가와요")
    println("Hello, ${if (args.isNotEmpty()) args[0] else "someone"}!")
    println("Hello, \"$name\"")
}
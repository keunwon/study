package com.ch02

import java.util.*

fun fizzBuzz(i : Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Flzz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun reverseIterator() {
    println("===== reverseIterator =====")
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }
    println("===========================")
}

fun untilIterator() {
    println("===== untilIterator =====")

    for (i in 1 until 100) {
        println(fizzBuzz(i))
    }

    println("=========================")
}

fun withIndexIterator() {
    println("===== withIndexIterator =====")

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }

    println("=============================")
}

fun mapIterator() {
    println("===== mapIterator =====")

    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'Z') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    println("=======================")
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) =  when (c) {
    in '0'..'9' -> "It's as digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know.."
}

fun main() {
    reverseIterator()
    untilIterator()
    mapIterator()
    withIndexIterator()
}
package com.keunwon.algorithm.programmers

class Lessons12969 {
}

fun main() {
    val (a, b) = readLine()!!.split(" ").map(String::toInt)
    repeat(b) {
        "*".repeat(a).also { println(it) }
    }
}

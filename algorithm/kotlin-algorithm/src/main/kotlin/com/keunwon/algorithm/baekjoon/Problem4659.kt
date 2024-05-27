package com.keunwon.algorithm.baekjoon

class Problem4659 {
    private val moeum = charArrayOf('a', 'e', 'i', 'o', 'u')

    fun solution(text: String): Boolean {
        if (text.all { it !in moeum }) return false

        for (i in 2 until text.length) {
            val arr = charArrayOf(text[i - 2], text[i - 1], text[i])

            if (arr.all { it in moeum }) return false
            if (arr.all { it !in moeum }) return false
        }

        for (i in 1 until text.length) {
            val str = "${text[i - 1]}${text[i]}"

            if (str[0] == str[1] && str !in arrayOf("ee", "oo")) return false
        }
        return true
    }
}

fun main() {
    while (true) {
        val text = readln()
        if (text == "end") break

        val valid = Problem4659().solution(text)
        val message = if (valid) "is acceptable." else "is not acceptable."
        println("<$text> $message")
    }
}

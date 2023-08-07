package com.keunwon.algorithm.baekjoon

/**
 * Title: 염색체
 * Level: 실버-3
 **/
class Problem9342 {
    fun solution(word: String): String {
        val regex = """^[A-F]?A+F+C+[A-F]?""".toRegex()
        return if (word.matches(regex)) "Infected!" else "Good"
    }
}

fun main() {
    val t = readLine()!!.toInt()

    repeat(t) {
        val word = readLine()!!
        Problem9342().solution(word).also(::println)
    }
}

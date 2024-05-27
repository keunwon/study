package com.keunwon.algorithm.baekjoon

class Problem1157 {
    fun solution(text: String): Char {
        val str = text.uppercase()
        val alphabets = IntArray(26).apply {
            str.forEach { c -> ++this[c - 'A'] }
        }
        var answer = '?'
        var max = 0

        for ((i, n) in alphabets.withIndex()) {
            if (max < n) {
                max = n
                answer = 'A' + i
            } else if (max == n) {
                answer = '?'
            }
        }
        return answer
    }
}

fun main() {
    val text = readln()
    println(Problem1157().solution(text))
}

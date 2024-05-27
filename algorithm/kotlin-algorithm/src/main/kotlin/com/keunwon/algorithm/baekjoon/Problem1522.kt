package com.keunwon.algorithm.baekjoon

import kotlin.math.min

class Problem1522 {
    fun solution(text: String): Int {
        val aCount = text.count { it == 'a' }
        var answer = aCount

        for (i in text.indices) {
            val bCount = (i until i + aCount).count { text[it % text.length] == 'b' }
            answer = min(answer, bCount)
        }
        return answer
    }
}

fun main() {
    val text = readln()
    println(Problem1522().solution(text))
}

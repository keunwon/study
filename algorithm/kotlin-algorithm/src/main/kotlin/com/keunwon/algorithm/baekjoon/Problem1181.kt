package com.keunwon.algorithm.baekjoon

/**
 * Title: 단어 정렬
 * Level: 실버-5
 **/
class Problem1181 {
    fun solution(words: Array<String>): Array<String> {
        return words.sortedWith(compareBy({ it.length }, { it }))
            .distinct()
            .toTypedArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val words = Array(n) { readLine()!! }
    Problem1181().solution(words).forEach { println(it) }
}

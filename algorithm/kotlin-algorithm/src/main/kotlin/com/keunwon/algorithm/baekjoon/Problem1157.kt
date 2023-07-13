package com.keunwon.algorithm.baekjoon

/**
 * Title: 단어 공부
 * Level: 브론즈-1
 **/
class Problem1157 {
    fun solution(word: String): Char {
        val alphabets = word.uppercase().toList()
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }

        return when {
            alphabets.size == 1 -> alphabets[0].first
            alphabets[0].second == alphabets[1].second -> '?'
            else -> alphabets[0].first
        }
    }
}

fun main() {
    val word = readLine()!!
    Problem1157().solution(word).also { println(it) }
}

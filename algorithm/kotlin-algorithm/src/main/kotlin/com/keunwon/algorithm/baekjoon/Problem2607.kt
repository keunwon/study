package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

/**
 * Title: 비슷한 단어
 * Level:  실버-3
 **/
class Problem2607 {
    fun solution(target: String, words: Array<String>): Int {
        val alphabets = ('A'..'Z').associateWith { key -> target.count { it == key } }
        return words.count { match(alphabets, target, it) }
    }

    private fun match(alphabets: Map<Char, Int>, target: String, word: String): Boolean {
        if (abs(target.length - word.length) > 1) return false

        val check = alphabets.toMutableMap()
        var count = 0

        for (c in word) {
            if (check.getValue(c) > 0) {
                check[c] = check.getValue(c) - 1
                count++
            }
        }

        return when (target.length) {
            word.length -> intArrayOf(word.length, word.length - 1).any { it == count }
            word.length + 1 -> word.length == count
            word.length - 1 -> target.length == count
            else -> false
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val target = readLine()!!
    val words = Array(n - 1) { readLine()!! }

    Problem2607().solution(target, words).also { println(it) }
}

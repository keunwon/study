package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

class Problem2607 {
    fun solution(target: String, words: Array<String>): Int {
        val alphabets = IntArray(26).apply { target.forEach { ++this[it - 'A'] } }
        return words.count { word -> match(alphabets, target, word) }
    }

    private fun match(alphabets: IntArray, target: String, word: String): Boolean {
        if (abs(target.length - word.length) > 1) return false

        val tmpAlphabets = alphabets.copyOf()
        var count = 0

        for (c in word) {
            if (tmpAlphabets[c - 'A'] > 0) {
                --tmpAlphabets[c - 'A']
                ++count
            }
        }

        return when (target.length) {
            word.length -> word.length == count || word.length - 1 == count
            word.length + 1 -> word.length == count
            word.length - 1 -> target.length == count
            else -> false
        }
    }
}

fun main() {
    val n = readln().toInt()
    val target = readln()
    val words = Array(n - 1) { readln() }

    println(Problem2607().solution(target, words))
}

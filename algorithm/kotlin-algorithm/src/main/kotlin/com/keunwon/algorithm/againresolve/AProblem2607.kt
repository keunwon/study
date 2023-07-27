package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class AProblem2607 {
    fun solution(target: String, words: Array<String>): Int {
        val alphabets = ('A'..'Z').associateWith { key -> target.count { it == key } }
        return words.count { match(alphabets, target, it) }
    }

    private fun match(alphabets: Map<Char, Int>, target: String, word: String): Boolean {
        if (abs(target.length - word.length) > 1) return false

        val map = alphabets.toMutableMap()
        var count = 0

        for (c in word) {
            if (map.getValue(c) > 0) {
                map[c] = map.getValue(c) - 1
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
    val arr = Array(n - 1) { readLine()!! }
    AProblem2607().solution(target, arr).also { println(it) }
}

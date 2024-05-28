package com.keunwon.algorithm.baekjoon

import kotlin.math.min

class Problem2179 {
    fun solution(words: Array<String>): Array<String> {
        var max = 0
        var index1 = 0
        var index2 = 0

        for (i in 0 until words.lastIndex) {
            val cur = words[i]

            for (j in i + 1 until words.size) {
                val next = words[j]
                val min = min(cur.length, next.length)
                var count = 0

                for (k in 0 until min) {
                    if (cur[k] != next[k]) break
                    ++count
                }

                if (max < count) {
                    max = count
                    index1 = i
                    index2 = j
                }
            }
        }
        return arrayOf(words[index1], words[index2])
    }
}

fun main() {
    val n = readln().toInt()
    val words = Array(n) { readln() }

    Problem2179().solution(words).forEach { println(it) }
}

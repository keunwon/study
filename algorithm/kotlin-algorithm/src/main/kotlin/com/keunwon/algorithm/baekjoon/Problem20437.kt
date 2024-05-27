package com.keunwon.algorithm.baekjoon

import kotlin.math.max
import kotlin.math.min

class Problem20437 {
    fun solution(w: String, k: Int): IntArray {
        if (k == 1) return intArrayOf(1, 1)

        var min = Int.MAX_VALUE
        var max = 0
        val countMap = w.groupingBy { it }.eachCount()

        for ((i, c) in w.withIndex()) {
            if (countMap.getOrDefault(c, 0) < k) continue

            var count = 1
            for (j in i + 1 until w.length) {
                if (c == w[j]) ++count
                if (count == k) {
                    min = min(min, j - i + 1)
                    max = max(max, j - i + 1)
                    break
                }
            }
        }

        if (min == Int.MAX_VALUE) return intArrayOf(-1)
        return intArrayOf(min, max)
    }
}

fun main() {
    repeat(readln().toInt()) {
        val w = readln()
        val k = readln().toInt()

        Problem20437().solution(w, k).also { println(it.joinToString(" ")) }
    }
}

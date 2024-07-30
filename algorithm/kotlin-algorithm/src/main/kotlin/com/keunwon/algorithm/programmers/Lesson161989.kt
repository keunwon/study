package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson161989 {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        val visited = BooleanArray(n) { true }.apply { section.forEach { this[it - 1] = false } }
        var result = 0

        for (i in 0 until n) {
            if (visited[i]) continue

            for (j in i until min(i + m, n)) {
                visited[j] = true
            }
            ++result
        }
        return result
    }
}

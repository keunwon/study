package com.keunwon.algorithm.programmers

class Lesson152996 {
    fun solution(weights: IntArray): Long {
        weights.sort()

        val map = mutableMapOf<Double, Int>()
        var answer = 0L

        for (w in weights) {
            val a = w * 1.0
            val b = (w * 2.0) / 3
            val c = (w * 2.0) / 4
            val d = (w * 3.0) / 4

            answer += doubleArrayOf(a, b, c, d).sumOf { map[it] ?: 0 }
            map[a] = map.getOrDefault(a, 0) + 1
        }
        return answer
    }
}
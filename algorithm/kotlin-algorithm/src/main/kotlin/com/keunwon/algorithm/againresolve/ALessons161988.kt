package com.keunwon.algorithm.againresolve

class ALessons161988 {
    fun solution(sequence: IntArray): Long {
        val dp1 = LongArray(sequence.size).apply { set(0, sequence[0].toLong()) }
        val dp2 = LongArray(sequence.size).apply { set(0, -sequence[0].toLong()) }
        var n = -1L

        for (i in 1 until sequence.size) {
            val multiply = sequence[i] * n

            dp1[i] = (dp1[i - 1] + multiply).coerceAtLeast(multiply)
            dp2[i] = (dp2[i - 1] + -multiply).coerceAtLeast(-multiply)
            n *= -1
        }
        return dp1.maxOf { it }.coerceAtLeast(dp2.maxOf { it })
    }
}

fun main() {
    ALessons161988().solution(
        intArrayOf(2, 3, -6, 1, 3, -1, 2, 4)
    ).also(::println)
}

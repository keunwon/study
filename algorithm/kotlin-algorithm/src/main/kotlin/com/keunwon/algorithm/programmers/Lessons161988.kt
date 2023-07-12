package com.keunwon.algorithm.programmers

/**
 * Title: 연속 펄스 부분 수열의 합
 * Level: 3
 **/
class Lessons161988 {
    fun solution(sequence: IntArray): Long {
        val dp1 = LongArray(sequence.size).apply { set(0, sequence[0].toLong()) }
        val dp2 = LongArray(sequence.size).apply { set(0, -sequence[0].toLong()) }

        var n = -1L
        for (i in 1 until sequence.size) {
            val num = sequence[i] * n

            dp1[i] = (dp1[i - 1] + num).coerceAtLeast(num)
            dp2[i] = (dp2[i - 1] + -num).coerceAtLeast(-num)
            n *= -1
        }
        return dp1.maxOf { it }.coerceAtLeast(dp2.maxOf { it })
    }
}

package algorithm.programmers

import kotlin.math.max

class Lesson161988 {
    fun solution(sequence: IntArray): Long {
        val dp1 = LongArray(sequence.size).apply { this[0] = sequence[0].toLong() }
        val dp2 = LongArray(sequence.size).apply { this[0] = -sequence[0].toLong() }
        var n = -1

        for (i in 1 until sequence.size) {
            val num = sequence[i].toLong() * n

            dp1[i] = max(dp1[i - 1] + num, num)
            dp2[i] = max(dp2[i - 1] + -num, -num)
            n *= -1
        }
        return max(dp1.maxOf { it }, dp2.maxOf { it })
    }
}

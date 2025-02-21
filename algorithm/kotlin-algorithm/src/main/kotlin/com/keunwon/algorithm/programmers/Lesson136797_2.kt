package com.keunwon.algorithm.programmers

class Lesson136797_2 {
    private val cost = arrayOf(
        intArrayOf(1, 7, 6, 7, 5, 4, 5, 3, 2, 3),
        intArrayOf(7, 1, 2, 4, 2, 3, 5, 4, 5, 6),
        intArrayOf(6, 2, 1, 2, 3, 2, 3, 5, 4, 5),
        intArrayOf(7, 4, 2, 1, 5, 3, 2, 6, 5, 4),
        intArrayOf(5, 2, 3, 5, 1, 2, 4, 2, 3, 5),
        intArrayOf(4, 3, 2, 3, 2, 1, 2, 3, 2, 3),
        intArrayOf(5, 5, 3, 2, 4, 2, 1, 5, 3, 2),
        intArrayOf(3, 4, 5, 6, 2, 3, 5, 1, 2, 4),
        intArrayOf(2, 5, 4, 5, 3, 2, 3, 2, 1, 2),
        intArrayOf(3, 6, 5, 4, 5, 3, 2, 4, 2, 1),
    )

    fun solution(numbers: String): Int {
        val dp = Array(10) { Array(10) { IntArray(numbers.length + 1) { 1e9.toInt() } } }
        dp[4][6][0] = 0

        for ((i, c) in numbers.withIndex()) {
            val num = c - '0'

            for (left in 0 until 10) {
                for (right in 0 until 10) {
                    if (left != right && dp[left][right][i] != 1e9.toInt()) {
                        dp[left][num][i + 1] = dp[left][num][i + 1]
                            .coerceAtMost(dp[left][right][i] + cost[right][num])

                        dp[num][right][i + 1] = dp[num][right][i + 1]
                            .coerceAtMost(dp[left][right][i] + cost[left][num])
                    }
                }
            }
        }

        return dp.minOf { arr -> arr.minOf { it[numbers.length] } }
    }
}

fun main() {
    Lesson136797_2().solution("1756").also(::println) // 10
    Lesson136797_2().solution("5123").also(::println) // 8
}

package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름: 에어컨
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/214289">에어컨 (Level-3)</a>
 **/
class Lesson214289 {
    fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
        val range = IntRange(t1 + 10, t2 + 10)
        val temp = temperature + 10
        val dp = Array(onboard.size) { IntArray(51) { 1e9.toInt() } }.apply { this[0][temp] = 0 }

        for (i in 0 until onboard.lastIndex) {
            for (j in 0..50) {
                if (onboard[i] == 1 && j !in range) continue

                dp[i + 1][j] = dp[i + 1][j].coerceAtMost(dp[i][j] + b)
                if (j > 0) dp[i + 1][j - 1] = dp[i + 1][j - 1].coerceAtMost(dp[i][j] + a)
                if (j < 50) dp[i + 1][j + 1] = dp[i + 1][j + 1].coerceAtMost(dp[i][j] + a)

                if (j < temp) {
                    dp[i + 1][j + 1] = dp[i + 1][j + 1].coerceAtMost(dp[i][j])
                } else if (j > temp) {
                    dp[i + 1][j - 1] = dp[i + 1][j - 1].coerceAtMost(dp[i][j])
                } else {
                    dp[i + 1][j] = dp[i + 1][j].coerceAtMost(dp[i][j])
                }
            }
        }

        return if (onboard.last() == 1) {
            range.minOf { dp.last()[it] }
        } else {
            dp.last().minOrNull()!!
        }
    }
}

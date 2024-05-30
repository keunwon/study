package com.keunwon.algorithm.programmers

class Lesson12914 {
    fun solution(n: Int): Long {
        val dp = LongArray(n + 4).apply {
            this[1] = 1L
            this[2] = 2L
            this[3] = 3L
        }

        for (i in 4..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567
        }
        return dp[n]
    }
}

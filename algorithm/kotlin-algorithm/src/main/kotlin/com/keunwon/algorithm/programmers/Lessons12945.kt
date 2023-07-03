package com.keunwon.algorithm.programmers

/**
 * Title: 피보나치 수
 * Level: 2
 **/
class Lessons12945 {
    fun solution(n: Int): Int {
        val dp = IntArray(n + 1).apply {
            this[1] = 1
            (2..n).forEach { this[it] = (this[it - 2] + this[it - 1]) % 1234567 }
        }
        return dp[n]
    }
}

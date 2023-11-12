package com.keunwon.algorithm.againresolve

class ALessons12914 {
    fun solution(n: Int): Long {
        val dp = LongArray(n + 2).apply {
            this[1] = 1
            this[2] = 2
        }
        for (i in 3..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567
        }
        return dp[n]
    }
}

fun main() {
    ALessons12914().solution(3).also(::println)
}

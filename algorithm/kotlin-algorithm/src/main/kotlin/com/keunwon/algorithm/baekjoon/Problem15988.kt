package com.keunwon.algorithm.baekjoon

/**
 * Title: 1, 2, 3 더하기 3
 * Level: 실버-2
 **/
class Problem15988 {
    fun solution(): LongArray {
        val dp = LongArray(1_000_001).apply {
            this[1] = 1
            this[2] = 2
            this[3] = 4
        }

        for (i in 4..1_000_000) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009
        }
        return dp
    }
}

fun main() {
    val dp = Problem15988().solution()
    val t = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        dp[n].also { println(it) }
    }
}

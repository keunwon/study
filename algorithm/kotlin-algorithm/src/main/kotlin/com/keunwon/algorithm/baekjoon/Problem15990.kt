package com.keunwon.algorithm.baekjoon

class Problem15990 {
    fun solution(): LongArray {
        val prefix = 1_000_000_009
        val dp = Array(100_001) { LongArray(4) }.apply {
            this[1][1] = 1
            this[2][2] = 1
            this[3][1] = 1
            this[3][2] = 1
            this[3][3] = 1
        }
        for (i in 4..100_000) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % prefix
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % prefix
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % prefix
        }
        return dp.map { it.sum() % prefix }.toLongArray()
    }
}

fun main() {
    val dp = Problem15990().solution()
    val t = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        dp[n].also { println(it) }
    }
}

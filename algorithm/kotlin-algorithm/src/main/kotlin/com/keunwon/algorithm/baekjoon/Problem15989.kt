package com.keunwon.algorithm.baekjoon

/**
 * Title: 1, 2, 3 더하기 4
 * Level: 실버-1
 **/
class Problem15989 {
    fun solution(): Array<IntArray> {
        val dp = Array(10_001) { IntArray(4) }.apply {
            this[1][1] = 1
            this[2][1] = 1
            this[2][2] = 1
            this[3][1] = 1
            this[3][2] = 1
            this[3][3] = 1
        }

        for (i in 4 until 10_001) {
            dp[i][1] = dp[i - 1][1]
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2]
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]
        }
        return dp
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val numbers = IntArray(n) { readLine()!!.toInt() }
    val answer = Problem15989().solution()

    numbers.forEach { println(answer[it].sum()) }
}

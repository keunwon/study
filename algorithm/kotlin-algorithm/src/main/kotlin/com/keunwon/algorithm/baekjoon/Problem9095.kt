package com.keunwon.algorithm.baekjoon

/**
 * Title: 1, 2, 3 더하기
 * Level: 실버-3
 **/
class Problem9095 {
    fun solution(): IntArray {
        val dp = IntArray(11).apply {
            this[1] = 1
            this[2] = 2
            this[3] = 4
        }
        for (i in 4..10) dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
        return dp
    }
}

fun main() {
    val dp = Problem9095().solution()
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }

    arr.forEach { println(dp[it]) }
}

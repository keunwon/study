package com.keunwon.algorithm.againresolve

class AProblem15989 {
    fun solution(): IntArray {
        val dp = Array(10_001) { IntArray(4) }.apply {
            this[1][1] = 1
            this[2][1] = 1
            this[2][2] = 1
            this[3][1] = 1
            this[3][2] = 1
            this[3][3] = 1
        }

        for (i in 4..10_000) {
            dp[i][1] = dp[i - 1][1]
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2]
            dp[i][3] = dp[i - 3].sum()
        }
        return dp.map { it.sum() }.toIntArray()
    }
}

fun main() {
    val dp = AProblem15989().solution()
    val t = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        dp[n].also { println(it) }
    }
}

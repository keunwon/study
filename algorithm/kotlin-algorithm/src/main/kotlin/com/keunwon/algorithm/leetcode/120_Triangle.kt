package com.keunwon.algorithm.leetcode

class `120_Triangle` {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val dp = Array(triangle.size) { IntArray(triangle.size) }.apply {
            this[0][0] = triangle[0][0]
        }

        for (i in 1 until dp.size) {
            for (j in 0..i) {
                when (j) {
                    0 -> {
                        dp[i][j] = dp[i - 1][j] + triangle[i][j]
                    }

                    i -> {
                        dp[i][j] = dp[i - 1][j - 1] + triangle[i][j]
                    }

                    else -> {
                        dp[i][j] = minOf(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j]
                    }
                }
            }
        }
        return dp.last().min()
    }
}

fun main() {
    `120_Triangle`().minimumTotal(
        listOf(
            listOf(2),
            listOf(3, 4),
            listOf(6, 5, 7),
            listOf(4, 1, 8, 3)
        )
    ).also { println(it) } // 11
}

package com.keunwon.algorithm.leetcode

import kotlin.math.max

class `221_Maximal_Square` {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val dp = Array(matrix.size) { IntArray(matrix[0].size) }
        var result = 0

        for (i in matrix.indices) {
            dp[i][0] = matrix[i][0] - '0'
            if (dp[i][0] == 1) result = 1
        }

        for (i in matrix[0].indices) {
            dp[0][i] = matrix[0][i] - '0'
            if (dp[0][i] == 1) result = 1
        }

        for (i in 1 until matrix.size) {
            for (j in 1 until matrix[0].size) {
                if (matrix[i][j] == '0') dp[i][j] = 0
                else dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1

                result = max(result, dp[i][j])
            }
        }
        return result * result
    }
}

fun main() {
    `221_Maximal_Square`().maximalSquare(
        arrayOf(
            charArrayOf('1', '0', '1', '0', '0'),
            charArrayOf('1', '0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1', '1'),
            charArrayOf('1', '0', '0', '1', '0')
        )
    ).also { println(it) }

    `221_Maximal_Square`().maximalSquare(
        arrayOf(
            charArrayOf('0', '1'),
            charArrayOf('1', '0')
        )
    ).also { println(it) }
}

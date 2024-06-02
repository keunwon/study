package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson118668 {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val maxAlp = problems.maxOf { it[0] }
        val maxCop = problems.maxOf { it[1] }
        val minAlp = min(alp, maxAlp)
        val minCop = min(cop, maxCop)
        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { 1e9.toInt() } }.apply {
            this[minAlp][minCop] = 0
        }

        for (i in minAlp..maxAlp) {
            for (j in minCop..maxCop) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = min(dp[i + 1][j], dp[i][j] + 1)
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = min(dp[i][j + 1], dp[i][j] + 1)
                }

                for ((alpReq, copReq, alpRwd, copRwd, cost) in problems) {
                    if (alpReq <= i && copReq <= j) {
                        val nextAlp = min(i + alpRwd, maxAlp)
                        val nextCop = min(j + copRwd, maxCop)
                        dp[nextAlp][nextCop] = min(dp[nextAlp][nextCop], dp[i][j] + cost)
                    }
                }
            }
        }
        return dp[maxAlp][maxCop]
    }
}
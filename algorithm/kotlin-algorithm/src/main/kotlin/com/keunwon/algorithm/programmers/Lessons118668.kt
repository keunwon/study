package com.keunwon.algorithm.programmers

/**
 * Title: 코딩 테스트 공부
 * Level: 3
 **/
class Lessons118668 {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val maxAlp = problems.maxOf { it[0] }
        val maxCop = problems.maxOf { it[1] }
        val minAlp = alp.coerceAtMost(maxAlp)
        val minCop = cop.coerceAtMost(maxCop)
        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { 1e9.toInt() } }.apply {
            this[minAlp][minCop] = 0
        }

        for (i in minAlp..maxAlp) {
            for (j in minCop..maxCop) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = dp[i + 1][j].coerceAtMost(dp[i][j] + 1)
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = dp[i][j + 1].coerceAtMost(dp[i][j] + 1)
                }

                problems.forEach { (alpReq, copReq, alpRwd, copRwd, cost) ->
                    if (alpReq <= i && copReq <= j) {
                        val nextAlp = maxAlp.coerceAtMost(i + alpRwd)
                        val nextCop = maxCop.coerceAtMost(j + copRwd)
                        dp[nextAlp][nextCop] = dp[nextAlp][nextCop].coerceAtMost(dp[i][j] + cost)
                    }
                }
            }
        }
        return dp[maxAlp][maxCop]
    }
}

package com.keunwon.algorithm.programmers

/**
 * Title: 카운트 다운
 * Level: 3
 **/
class Lessons131129 {
    private lateinit var dp: Array<IntArray>

    fun solution(target: Int): IntArray {
        this.dp = Array(target + 1) { intArrayOf(INF, 0) }.apply {
            this[0][0] = 0
        }
        return dfs(target)
    }

    private fun dfs(target: Int): IntArray {
        if (dp[target][0] != INF) return dp[target].copyOf()

        if (target >= 50) {
            val (a, b) = dfs(target - 50).map(Int::inc)
            if (check(a, b, target)) {
                dp[target][0] = a
                dp[target][1] = b
            }
        }

        val n = if (target >= 20) 20 else target
        for (i in n downTo 1) {
            for (j in 1..3) {
                if (target < i * j) continue

                val (a, b) = dfs(target - i * j).apply {
                    this[0]++
                    if (j == 1) this[1]++
                }
                if (check(a, b, target)) {
                    dp[target][0] = a
                    dp[target][1] = b
                }
            }
        }
        return dp[target].copyOf()
    }

    private fun check(a: Int, b: Int, n: Int): Boolean {
        return a < dp[n][0] || a == dp[n][0] && b > dp[n][1]
    }

    companion object {
        private const val INF = 100_001
    }
}

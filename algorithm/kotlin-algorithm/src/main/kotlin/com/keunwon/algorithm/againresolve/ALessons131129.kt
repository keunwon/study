package com.keunwon.algorithm.againresolve

class ALessons131129 {
    private lateinit var dp: Array<IntArray>

    fun solution(target: Int): IntArray {
        dp = Array(target + 1) { intArrayOf(INF, 0) }.apply {
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

        val limit = if (target >= 20) 20 else target
        for (i in limit downTo 1) {
            for (j in 1..3) {
                val num = i * j
                if (target < num) continue

                val (a, b) = dfs(target - num).apply {
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

    private fun check(a: Int, b: Int, n: Int): Boolean = a < dp[n][0] || (a == dp[n][0] && dp[n][1] < b)

    companion object {
        private const val INF = 100_001
    }
}

fun main() {
    ALessons131129().solution(51).also { println(it.contentToString()) }
    ALessons131129().solution(21).also { println(it.contentToString()) }
    ALessons131129().solution(58).also { println(it.contentToString()) }
}

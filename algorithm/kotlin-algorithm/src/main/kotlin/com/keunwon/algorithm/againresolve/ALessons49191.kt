package com.keunwon.algorithm.againresolve

class ALessons49191 {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val dp = Array(n + 1) { IntArray(n + 1) }.apply {
            results.forEach { (a, b) ->
                this[a][b] = 1
                this[b][a] = -1
            }
        }
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    if (dp[j][i] == 1 && dp[i][k] == 1) {
                        dp[j][k] = 1
                        dp[k][j] = -1
                    }
                    if (dp[j][i] == -1 && dp[i][k] == -1) {
                        dp[j][k] = -1
                        dp[k][j] = 1
                    }
                }
            }
        }
        return dp.count { arr -> arr.count { it == 0 } == 2 }
    }
}

fun main() {
    ALessons49191().solution(
        5,
        arrayOf(
            intArrayOf(4, 3),
            intArrayOf(4, 2),
            intArrayOf(3, 2),
            intArrayOf(1, 2),
            intArrayOf(2, 5)
        )
    ).also(::println)
}

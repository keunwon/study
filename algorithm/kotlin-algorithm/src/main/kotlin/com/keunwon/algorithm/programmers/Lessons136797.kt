package com.keunwon.algorithm.programmers

/**
 * Title: 숫자 타자 대회
 * Level: 3
 **/
class Lessons136797 {
    private lateinit var numbers: String
    private val dp = Array(100_000) { Array(10) { IntArray(10) } }
    private val cost = arrayOf(
        intArrayOf(1, 7, 6, 7, 5, 4, 5, 3, 2, 3),
        intArrayOf(7, 1, 2, 4, 2, 3, 5, 4, 5, 6),
        intArrayOf(6, 2, 1, 2, 3, 2, 3, 5, 4, 5),
        intArrayOf(7, 4, 2, 1, 5, 3, 2, 6, 5, 4),
        intArrayOf(5, 2, 3, 5, 1, 2, 4, 2, 3, 5),
        intArrayOf(4, 3, 2, 3, 2, 1, 2, 3, 2, 3),
        intArrayOf(5, 5, 3, 2, 4, 2, 1, 5, 3, 2),
        intArrayOf(3, 4, 5, 6, 2, 3, 5, 1, 2, 4),
        intArrayOf(2, 5, 4, 5, 3, 2, 3, 2, 1, 2),
        intArrayOf(3, 6, 5, 4, 5, 3, 2, 4, 2, 1),
    )

    fun solution(numbers: String): Int {
        this.numbers = numbers
        return dfs(0, 4, 6)
    }

    private fun dfs(depth: Int, left: Int, right: Int): Int {
        if (numbers.length == depth) return 0
        if (dp[depth][left][right] != 0) return dp[depth][left][right]

        val dest = numbers[depth] - '0'
        var result = Int.MAX_VALUE

        if (right != dest) {
            val tmp = dfs(depth + 1, dest, right) + cost[left][dest]
            result = result.coerceAtMost(tmp)
        }
        if (left != dest) {
            val tmp = dfs(depth + 1, left, dest) + cost[right][dest]
            result = result.coerceAtMost(tmp)
        }
        return result.also { dp[depth][left][right] = it }
    }
}

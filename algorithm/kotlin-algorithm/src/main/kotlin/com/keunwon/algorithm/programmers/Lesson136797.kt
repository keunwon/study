package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson136797 {
    private lateinit var numbers: String
    private lateinit var dp: Array<Array<IntArray>>

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
        this.dp = Array(10) { Array(10) { IntArray(numbers.length + 1) { -1 } } }
        return dfs(0, 4, 6)
    }

    private fun dfs(depth: Int, left: Int, right: Int): Int {
        if (depth == numbers.length) return 0
        if (dp[left][right][depth] != -1) return dp[left][right][depth]

        val num = numbers[depth] - '0'
        var result = Int.MAX_VALUE

        if (num != right) {
            result = min(result, dfs(depth + 1, num, right) + cost[left][num])
        }
        if (num != left) {
            result = min(result, dfs(depth + 1, left, num) + cost[right][num])
        }

        dp[left][right][depth] = result
        return result
    }
}

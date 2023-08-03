package com.keunwon.algorithm.againresolve

class ALessons136797 {
    private val costs = arrayOf(
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
    private lateinit var numbers: String
    private lateinit var dp: Array<Array<IntArray>>

    fun solution(numbers: String): Int {
        this.numbers = numbers
        this.dp = Array(numbers.length + 1) {
            Array(10) { IntArray(10) }
        }

        return dfs(0, 4, 6)
    }

    private fun dfs(depth: Int, left: Int, right: Int): Int {
        if (depth == numbers.length) return 0
        if (dp[depth][left][right] != 0) return dp[depth][left][right]

        val dest = numbers[depth] - '0'
        var result = Int.MAX_VALUE

        if (right != dest) {
            val n = dfs(depth + 1, dest, right) + costs[left][dest]
            result = result.coerceAtMost(n)
        }
        if (left != dest) {
            val n = dfs(depth + 1, left, dest) + costs[right][dest]
            result = result.coerceAtMost(n)
        }
        return result.also { dp[depth][left][right] = it }
    }
}

fun main() {
    arrayOf("1756", "5123").forEach { numbers ->
        ALessons136797().solution(numbers).also { println(it) }
    }
}

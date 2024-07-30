package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson72416 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var sales: IntArray

    private val dp = Array(300_001) { IntArray(2) }

    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        this.graph = Array(sales.size + 1) { mutableListOf<Int>() }.apply {
            links.forEach { (a, b) -> this[a].add(b) }
        }
        this.sales = intArrayOf(0, *sales)

        dfs(1)
        return dp[1].minOrNull() ?: 0
    }

    private fun dfs(cur: Int) {
        dp[cur][0] = 0
        dp[cur][1] = sales[cur]

        if (graph[cur].isEmpty()) return

        var num = 1e9.toInt()
        for (child in graph[cur]) {
            dfs(child)

            if (dp[child][0] < dp[child][1]) {
                dp[cur][0] += dp[child][0]
                dp[cur][1] += dp[child][0]
                num = min(num, dp[child][1] - dp[child][0])
            } else {
                dp[cur][0] += dp[child][1]
                dp[cur][1] += dp[child][1]
                num = 0
            }
        }
        dp[cur][0] += num
    }
}

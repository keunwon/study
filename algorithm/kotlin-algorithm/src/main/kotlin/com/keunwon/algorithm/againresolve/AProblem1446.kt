package com.keunwon.algorithm.againresolve

class AProblem1446 {
    fun solution(d: Int, roads: Array<Triple<Int, Int, Int>>): Int {
        roads.sortWith(compareBy({ it.first }, { it.second }))

        val dp = IntArray(10_001) { 10_001 }.apply { set(0, 0) }
        var cur = 0
        var index = 0

        while (cur < d) {
            if (index == roads.size) {
                11501
                cur++
                dp[cur] = dp[cur].coerceAtMost(dp[cur - 1] + 1)
                continue
            }

            val (src, dest, distance) = roads[index]
            if (cur == src) {
                dp[dest] = dp[dest].coerceAtMost(dp[src] + distance)
                index++
            } else {
                cur++
                dp[cur] = dp[cur].coerceAtMost(dp[cur - 1] + 1)
            }
        }
        return dp[d]
    }
}

fun main() {
    val (n, d) = readLine()!!.split(" ").map { it.toInt() }
    val roads = Array(n) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    AProblem1446().solution(d, roads).also { println(it) }
}

package com.keunwon.algorithm.baekjoon

/**
 * Title: 지름길
 * Level: 실버-1
 **/
class Problem1446 {
    fun solution(d: Int, shortcuts: Array<IntArray>): Int {
        shortcuts.sortWith(compareBy({ it[0] }, { it[1] }))

        val dp = IntArray(10_001) { 10_001 }.apply { set(0, 0) }
        var cur = 0
        var idx = 0

        while (cur < d) {
            if (idx == shortcuts.size) {
                cur++
                dp[cur] = dp[cur].coerceAtMost(dp[cur - 1] + 1)
                continue
            }

            val (src, dest, distance) = shortcuts[idx]
            if (cur == src) {
                idx++
                dp[dest] = dp[dest].coerceAtMost(dp[cur] + distance)
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
    val shortcuts = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem1446().solution(d, shortcuts).also { println(it) }
}

package com.keunwon.algorithm.programmers

/**
 * Title: 공 이동 시뮬레이션
 * Level: 4
 **/
class Lessons87391 {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var (r1, r2, c1, c2) = intArrayOf(x, x, y, y)

        for (i in queries.lastIndex downTo 0) {
            val (dir, num) = queries[i]
            when (dir) {
                0 -> {
                    if (c1 != 0) c1 += num
                    c2 = (c2 + num).coerceAtMost(m - 1)
                }
                1 -> {
                    if (c2 != m - 1) c2 -= num
                    c1 = (c1 - num).coerceAtLeast(0)
                }
                2 -> {
                    if (r1 != 0) r1 += num
                    r2 = (r2 + num).coerceAtMost(n - 1)
                }
                3 -> {
                    if (r2 != n - 1) r2 -= num
                    r1 = (r1 - num).coerceAtLeast(0)
                }
            }
            if (r1 !in 0 until n || r2 !in 0 until n || c1 !in 0 until m || c2 !in 0 until m) return 0
        }
        return (r2 - r1 + 1L) * (c2 - c1 + 1L)
    }
}

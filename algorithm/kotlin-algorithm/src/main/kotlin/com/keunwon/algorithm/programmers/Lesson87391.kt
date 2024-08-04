package com.keunwon.algorithm.programmers

import kotlin.math.max
import kotlin.math.min

class Lesson87391 {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var (r1, c1) = x to y
        var (r2, c2) = x to y

        for ((command, dx) in queries.reversed()) {
            when (command) {
                0 -> {
                    if (c1 != 0) c1 += dx
                    c2 = min(c2 + dx, m - 1)
                    if (c1 >= m) return 0
                }

                1 -> {
                    if (c2 != m - 1) c2 -= dx
                    c1 = max(0, c1 - dx)
                    if (c2 < 0) return 0
                }

                2 -> {
                    if (r1 != 0) r1 += dx
                    r2 = min(n - 1, r2 + dx)
                    if (r1 >= n) return 0
                }

                3 -> {
                    if (r2 != n - 1) r2 -= dx
                    r1 = max(0, r1 - dx)
                    if (r2 < 0) return 0
                }
            }
        }
        return (r2 - r1 + 1).toLong() * (c2 - c1 + 1)
    }
}

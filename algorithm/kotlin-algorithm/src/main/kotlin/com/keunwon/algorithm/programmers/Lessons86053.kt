package com.keunwon.algorithm.programmers

/**
 * Title: 금과 은 운반하기
 * Level: 3
 **/
class Lessons86053 {
    fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
        var left = 0L
        var right = (40e9 * 10e5).toLong()

        while (left <= right) {
            val mid = (left + right) / 2

            if (check(mid, a, b, g, s, w, t)) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    private fun check(
        hour: Long,
        a: Int,
        b: Int,
        g: IntArray,
        s: IntArray,
        w: IntArray,
        t: IntArray,
    ): Boolean {
        var gold = 0L
        var silver = 0L
        var total = 0L

        for ((i, time) in t.withIndex()) {
            val count = run {
                val double = time * 2
                hour / double + if (hour % double >= time) 1 else 0
            }
            val weight = (w[i] * count).coerceAtMost(g[i].toLong() + s[i])

            total += weight
            gold += weight.coerceAtMost(g[i].toLong())
            silver += weight.coerceAtMost(s[i].toLong())
        }
        return (a + b) <= total && a <= gold && b <= silver
    }
}

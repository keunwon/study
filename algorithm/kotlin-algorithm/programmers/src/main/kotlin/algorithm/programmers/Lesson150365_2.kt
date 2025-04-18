package algorithm.programmers

import kotlin.math.abs

class Lesson150365_2 {
    private var n = 0
    private var m = 0
    private var r = 0
    private var c = 0
    private var k = 0
    private var result = ""

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        this.n = n
        this.m = m
        this.r = r
        this.c = c
        this.k = k

        if (manhattanDistance(x, y, r, c) > k) return "impossible"

        dfs(x, y, "")
        return result.ifBlank { "impossible" }
    }

    private fun dfs(x: Int, y: Int, route: String) {
        if (result.isNotBlank()) return

        val ret = k - route.length
        val diff = manhattanDistance(x, y, r, c)

        if (ret < diff || ret % 2 != diff % 2) return

        if (route.length == k) {
            result = route
            return
        }

        for ((dir, mr, mc) in moves) {
            val nr = x + mr
            val nc = y + mc

            if (nr in 1..n && nc in 1..m) {
                dfs(nr, nc, "$route$dir")
            }
        }
    }

    private fun manhattanDistance(r1: Int, c1: Int, r2: Int, c2: Int): Int = abs(r1 - r2) + abs(c1 - c2)

    companion object {
        private val moves = arrayOf(
            Triple('d', 1, 0),
            Triple('l', 0, -1),
            Triple('r', 0, 1),
            Triple('u', -1, 0),
        )
    }
}

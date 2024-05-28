package com.keunwon.algorithm.baekjoon

import kotlin.math.max

class Problem1987 {
    private lateinit var map: Array<CharArray>

    private val alphabets = BooleanArray(26)
    private var answer = 1

    fun solution(map: Array<CharArray>): Int {
        this.map = map

        alphabets[map[0][0] - 'A'] = true
        dfs(1, 0, 0)
        return answer
    }

    private fun dfs(depth: Int, r: Int, c: Int) {
        answer = max(answer, depth)

        for ((mr, mc) in moves) {
            val nr = r + mr
            val nc = c + mc

            if (nr !in map.indices || nc !in map[0].indices) continue
            if (alphabets[map[nr][nc] - 'A']) continue

            alphabets[map[nr][nc] - 'A'] = true
            dfs(depth + 1, nr, nc)
            alphabets[map[nr][nc] - 'A'] = false
        }
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val map = Array(r) { readln().toCharArray() }

    println(Problem1987().solution(map))
}

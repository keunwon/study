package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 쉬운 최단거리
 * Level: 실버-1
 **/
class Problem14940 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Array<IntArray> {
        val dp = Array(map.size) { IntArray(map[0].size) { -1 } }
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        val queue = LinkedList<IntArray>()

        with(startPosition(map)) {
            val (sr, sc) = this
            dp[sr][sc] = 0
            visited[sr][sc] = true
            queue.offer(intArrayOf(sr, sc, 0))
        }

        while (queue.isNotEmpty()) {
            val (r, c, d) = queue.poll()

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] != 1) continue

                visited[nr][nc] = true
                dp[nr][nc] = d + 1
                queue.offer(intArrayOf(nr, nc, d + 1))
            }
        }

        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] == 0) dp[i][j] = 0
            }
        }
        return dp
    }

    private fun startPosition(map: Array<IntArray>): Pair<Int, Int> {
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == 2) return i to j
            }
        }
        error("시작지점을 찾을 수 없습니다.")
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem14940().solution(map).forEach { println(it.joinToString(" ")) }
}

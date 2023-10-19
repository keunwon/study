package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 녹색 옷 입은 애가 젤다지?
 * Level: 골드-4
 **/
class Problem4485 {
    fun solution(arr: Array<IntArray>): Int {
        val dp = Array(arr.size) { IntArray(arr[0].size) { Int.MAX_VALUE } }
        val queue = PriorityQueue<Position>(compareBy { it.distance })

        dp[0][0] = arr[0][0]
        queue.offer(Position(0, 0, arr[0][0]))

        while (queue.isNotEmpty()) {
            val (r, c, d) = queue.poll()

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr !in arr.indices || nc !in arr[0].indices) continue
                if (arr[nr][nc] + d >= dp[nr][nc]) continue

                dp[nr][nc] = arr[nr][nc] + d
                queue.offer(Position(nr, nc, arr[nr][nc] + d))
            }
        }
        return dp[arr.lastIndex][arr[0].lastIndex]
    }

    private data class Position(val r: Int, val c: Int, val distance: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    var idx = 0
    while (true) {
        val n = readLine()!!.toInt()

        if (n == 0) break
        idx++

        val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
        Problem4485().solution(arr).also { println("Problem $idx: $it") }
    }
}

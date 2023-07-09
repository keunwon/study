package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 경주로 건설
 * Level: 3
 **/
class Lessons67259 {
    fun solution(board: Array<IntArray>): Int {
        val dp = Array(board.size) {
            Array(board[0].size) { IntArray(4) { Int.MAX_VALUE } }
        }.apply {
            this[0][0][1] = 0
            this[0][0][2] = 0
        }
        val queue = LinkedList<Position>().apply {
            offer(Position(0, 0, 1))
            offer(Position(0, 0, 2))
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((dir, r, c) in moves) {
                val nr = cur.r + r
                val nc = cur.c + c
                val cost = dp[cur.r][cur.c][cur.dir] + if (cur.dir == dir) 100 else 600

                if (nr !in board.indices || nc !in board[0].indices) continue
                if (board[nr][nc] == 1) continue
                if (dp[nr][nc][dir] <= cost) continue

                dp[nr][nc][dir] = cost
                queue.offer(Position(nr, nc, dir))
            }
        }
        return dp[board.lastIndex][board[0].lastIndex].minOf { it }
    }

    data class Position(val r: Int, val c: Int, val dir: Int)

    companion object {
        private val moves = arrayOf(
            intArrayOf(0, -1, 0),
            intArrayOf(1, 0, 1),
            intArrayOf(2, 1, 0),
            intArrayOf(3, 0, -1),
        )
    }
}

package com.keunwon.algorithm.programmers

import java.util.*

class Lesson67259 {
    fun solution(board: Array<IntArray>): Int {
        val queue = LinkedList<Node>().apply {
            offer(Node(0, 0, 1))
            offer(Node(0, 0, 2))
        }
        val dp = Array(board.size) {
            Array(board[0].size) { IntArray(4) { 1e9.toInt() } }
        }.apply {
            this[0][0][1] = 0
            this[0][0][2] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == board.lastIndex && cur.c == board[0].lastIndex) continue

            for (dir in moves.indices) {
                val (mr, mc) = moves[dir]
                val nr = cur.r + mr
                val nc = cur.c + mc
                val cost = dp[cur.r][cur.c][cur.dir] + if (cur.dir == dir) 100 else 600

                if (nr !in board.indices || nc !in board[0].indices) continue
                if (board[nr][nc] == 1) continue
                if (dp[nr][nc][dir] <= cost) continue

                dp[nr][nc][dir] = cost
                queue.offer(Node(nr, nc, dir))
            }
        }
        return dp[board.lastIndex][board[0].lastIndex].minOrNull() ?: 0
    }

    private data class Node(val r: Int, val c: Int, val dir: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
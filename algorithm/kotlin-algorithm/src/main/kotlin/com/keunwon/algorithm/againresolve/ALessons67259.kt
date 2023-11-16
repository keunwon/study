package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons67259 {
    fun solution(board: Array<IntArray>): Int {
        val queue = LinkedList<Position>().apply {
            offer(Position(0, 0, 1))
            offer(Position(0, 0, 2))
        }
        val dp = Array(board.size) {
            Array(board[0].size) { IntArray(4) { Int.MAX_VALUE } }
        }.apply {
            this[0][0][1] = 0
            this[0][0][2] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((dir, move) in moves.withIndex()) {
                val (mr, mc) = move
                val (nr, nc) = Pair(cur.r + mr, cur.c + mc)
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

    private data class Position(val r: Int, val c: Int, val dir: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    ALessons67259().solution(
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
    ).also(::println)

    ALessons67259().solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 1, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 0, 0)
        )
    ).also(::println)

    ALessons67259().solution(
        arrayOf(
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0)
        )
    ).also(::println)

    ALessons67259().solution(
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 0),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 0, 0, 1, 0, 1),
            intArrayOf(0, 1, 0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0, 0)
        )
    ).also(::println)
}

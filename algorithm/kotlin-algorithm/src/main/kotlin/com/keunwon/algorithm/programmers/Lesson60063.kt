package com.keunwon.algorithm.programmers

import java.util.LinkedList

class Lesson60063 {
    fun solution(board: Array<IntArray>): Int {
        val visited = Array(board.size) { Array(board[0].size) { BooleanArray(2) } }.apply {
            this[0][0][0] = true
        }
        val q = LinkedList<Node>().apply { offer(Node(0, 0, 0, 1, 0, 0)) }
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        while (q.isNotEmpty()) {
            val cur = q.poll().also { println(it) }

            if ((cur.r1 == board.lastIndex && cur.c1 == board[0].lastIndex) ||
                (cur.r2 == board.lastIndex && cur.c2 == board[0].lastIndex)
            ) {
                return cur.count
            }

            for (dir in moves.indices) {
                val (mr, mc) = moves[dir]
                val r1 = cur.r1 + mr
                val c1 = cur.c1 + mc
                val r2 = cur.r2 + mr
                val c2 = cur.c2 + mc

                if (!isRange(r1, c1, r2, c2, board) || board[r1][c1] == 1 || board[r2][c2] == 1) {
                    continue
                }

                if (!visited[r1][c1][cur.dir] || !visited[r2][c2][cur.dir]) {
                    visited[r1][c1][cur.dir] = true
                    visited[r2][c2][cur.dir] = true
                    q.offer(Node(r1, c1, r2, c2, cur.dir, cur.count + 1))
                }

                val reverseDir = cur.dir xor 1
                if ((cur.dir == 0 && (dir == 0 || dir == 2)) || (cur.dir == 1 && (dir == 1 || dir == 3))) {
                    if (!visited[r1][c1][reverseDir] || !visited[cur.r1][cur.c1][reverseDir]) {
                        visited[r1][c1][reverseDir] = true
                        visited[cur.r1][cur.c1][reverseDir] = true
                        q.offer(Node(r1, c1, cur.r1, cur.c1, reverseDir, cur.count + 1))
                    }

                    if (!visited[cur.r2][cur.c2][reverseDir] || !visited[r2][c2][reverseDir]) {
                        visited[cur.r2][cur.c2][reverseDir] = true
                        visited[r2][c2][reverseDir] = true
                        q.offer(Node(cur.r2, cur.c2, r2, c2, reverseDir, cur.count + 1))
                    }
                }
            }
        }

        return -1
    }

    private fun isRange(r1: Int, c1: Int, r2: Int, c2: Int, board: Array<IntArray>): Boolean =
        r1 in board.indices && c1 in board[0].indices && r2 in board.indices && c2 in board[0].indices

    private data class Node(
        val r1: Int,
        val c1: Int,
        val r2: Int,
        val c2: Int,
        val dir: Int,
        val count: Int,
    )
}

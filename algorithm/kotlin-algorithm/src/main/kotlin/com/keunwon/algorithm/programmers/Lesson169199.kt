package com.keunwon.algorithm.programmers

import java.util.*

class Lesson169199 {
    fun solution(board: Array<String>): Int {
        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 'R') return bfs(Node(i, j, 0), board)
            }
        }
        return -1
    }

    private fun bfs(start: Node, board: Array<String>): Int {
        val queue = LinkedList<Node>().apply { offer(start) }
        val visited = Array(board.size) { BooleanArray(board[0].length) }.apply {
            this[start.r][start.c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (board[cur.r][cur.c] == 'G') return cur.d

            for ((mr, mc) in moves) {
                var nr = cur.r
                var nc = cur.c
                val valid = {
                    val tr = nr + mr
                    val tc = nc + mc
                    tr in board.indices && tc in board[0].indices && board[tr][tc] != 'D'
                }

                while (valid()) {
                    nr += mr
                    nc += mc
                }
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.d + 1))
                }
            }
        }
        return -1
    }

    private data class Node(val r: Int, val c: Int, val d: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 리코쳇 로봇
 * Level: 2
 **/
class Lessons169199 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(board: Array<String>): Int {
        val start = IntArray(2)
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] == 'R') {
                    start[0] = i
                    start[1] = j
                    break
                }
            }
        }
        return bfs(start, board)
    }

    private fun bfs(start: IntArray, board: Array<String>): Int {
        val queue = LinkedList<IntArray>().apply { offer(start + 0) }
        val visited = Array(board.size) { BooleanArray(board[0].length) }.apply { this[start[0]][start[1]] = true }

        while (queue.isNotEmpty()) {
            val (r, c, d) = queue.poll()

            if (board[r][c] == 'G') return d

            for ((mr, mc) in moves) {
                var nr = r
                var nc = c
                val validBoard = {
                    val tr = nr + mr
                    val tc = nc + mc
                    tr in board.indices && tc in board[0].indices && board[tr][tc] != 'D'
                }

                while (validBoard()) {
                    nr += mr
                    nc += mc
                }
                if (visited[nr][nc]) continue

                visited[nr][nc] = true
                queue.offer(intArrayOf(nr, nc, d + 1))
            }
        }
        return -1
    }
}

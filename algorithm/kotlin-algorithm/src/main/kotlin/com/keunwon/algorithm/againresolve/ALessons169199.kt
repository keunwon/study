package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons169199 {
    fun solution(board: Array<String>): Int {
        val queue = LinkedList<Node>()
        val visited = Array(board.size) { BooleanArray(board[0].length) }

        startPosition(board).let { (r, c) ->
            queue.offer(Node(r, c, 0))
            visited[r][c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            if (board[cur.r][cur.c] == 'G') return cur.distance

            for ((mr, mc) in moves) {
                var (nr, nc) = Pair(cur.r, cur.c)
                val valid = {
                    val tr = nr + mr
                    val tc = nc + mc
                    tr in board.indices && tc in board[0].indices && board[tr][tc] != 'D'
                }

                while (valid()) {
                    nr += mr
                    nc += mc
                }
                if (visited[nr][nc]) continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc, cur.distance + 1))
            }
        }
        return -1
    }

    private fun startPosition(board: Array<String>): Pair<Int, Int> {
        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 'R') return i to j
            }
        }
        error("'R' 존재하지 않습니다.")
    }

    private data class Node(val r: Int, val c: Int, val distance: Int = 0)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    ALessons169199().solution(
        arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")
    ).also(::println)

    ALessons169199().solution(
        arrayOf(".D.R", "....", ".G..", "...D")
    ).also(::println)
}

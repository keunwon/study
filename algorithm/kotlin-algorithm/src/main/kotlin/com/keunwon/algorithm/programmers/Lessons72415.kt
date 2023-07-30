package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 카드 짝 맞추기
 * Level: 3
 **/
class Lessons72415 {
    private lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board
        return dfs(Node(r, c, 0))
    }

    private fun dfs(src: Node): Int {
        var result = INF

        for (id in 1..6) {
            val list = mutableListOf<Node>().apply {
                for (i in board.indices) {
                    for (j in board[0].indices) {
                        if (board[i][j] == id) add(Node(i, j, 0))
                    }
                }
            }
            if (list.isEmpty()) continue

            val dist1 = bfs(src, list[0]) + bfs(list[0], list[1]) + 2
            val dist2 = bfs(src, list[1]) + bfs(list[1], list[0]) + 2

            list.forEach { (a, b) -> board[a][b] = 0 }
            result = result
                .coerceAtMost(dist1 + dfs(list[1]))
                .coerceAtMost(dist2 + dfs(list[0]))
            list.forEach { (a, b) -> board[a][b] = id }
        }
        return if (result == INF) 0 else result
    }

    private fun bfs(src: Node, dest: Node): Int {
        val queue = LinkedList<Node>().apply { offer(src) }
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == dest.r && cur.c == dest.c) return cur.count

            for ((mr, mc) in moves) {
                var nr = cur.r + mr
                var nc = cur.c + mc

                if (!isRange(nr, nc)) continue

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.count + 1))
                }

                while (isRange(nr + mr, nc + mc) && board[nr][nc] == 0) {
                    nr += mr
                    nc += mc
                }

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.count + 1))
                }
            }
        }
        return INF
    }

    private fun isRange(r: Int, c: Int): Boolean {
        return r in board.indices && c in board[0].indices
    }

    private data class Node(val r: Int, val c: Int, val count: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        private const val INF = 100
    }
}

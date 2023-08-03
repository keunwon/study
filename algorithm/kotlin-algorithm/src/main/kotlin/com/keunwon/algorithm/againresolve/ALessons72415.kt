package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons72415 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board
        return dfs(Node(r, c, 0))
    }

    private fun dfs(src: Node): Int {
        var answer = Int.MAX_VALUE

        for (id in 1..6) {
            val list = mutableListOf<Node>().apply {
                for (i in board.indices) {
                    for (j in board[0].indices) {
                        if (board[i][j] == id) add(Node(i, j, 0))
                    }
                }
            }
            if (list.isEmpty()) continue

            val (first, second) = list
            val dist1 = bfs(src, first) + bfs(first, second) + 2
            val dist2 = bfs(src, second) + bfs(second, first) + 2

            list.forEach { (a, b) -> board[a][b] = 0 }
            answer = answer
                .coerceAtMost(dist1 + dfs(second))
                .coerceAtMost(dist2 + dfs(first))
            list.forEach { (a, b) -> board[a][b] = id }
        }
        return if (answer == Int.MAX_VALUE) 0 else answer
    }

    private fun bfs(src: Node, dest: Node): Int {
        val queue = LinkedList<Node>()
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        queue.offer(src)
        visited[src.r][src.c] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == dest.r && cur.c == dest.c) return cur.count

            for ((mr, mc) in moves) {
                var (nr, nc) = cur.r + mr to cur.c + mc

                if (!isRange(nr, nc)) continue

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.count + 1))
                }

                while (board[nr][nc] == 0 && isRange(nr + mr, nc + mc)) {
                    nr += mr
                    nc += mc
                }

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.count + 1))
                }
            }
        }
        error("")
    }

    private fun isRange(r: Int, c: Int): Boolean {
        return r in board.indices && c in board[0].indices
    }

    private data class Node(val r: Int, val c: Int, val count: Int)
}

fun main() {
    val board = arrayOf(
        intArrayOf(1, 0, 0, 3),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(0, 0, 0, 2),
        intArrayOf(3, 0, 1, 0),
    )
    val r = 1
    val c = 0

    ALessons72415().solution(board, r, c).also { println(it) }
}

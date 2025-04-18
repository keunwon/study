package algorithm.programmers

import java.util.*

class Lesson72415 {
    private lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        this.board = board
        return dfs(Node(r, c, 0))
    }

    private fun dfs(start: Node): Int {
        var answer = INF
        for (id in 1..6) {
            val nodes = mutableListOf<Node>().apply {
                for (i in board.indices) {
                    for ((j, type) in board[i].withIndex()) {
                        if (id == type) add(Node(i, j, 0))
                    }
                }
            }
            if (nodes.isEmpty()) continue

            val dist1 = bfs(start, nodes[0]) + bfs(nodes[0], nodes[1]) + 2
            val dist2 = bfs(start, nodes[1]) + bfs(nodes[1], nodes[0]) + 2

            nodes.forEach { (r, c) -> board[r][c] = 0 }
            answer = answer
                .coerceAtMost(dist1 + dfs(nodes[1]))
                .coerceAtMost(dist2 + dfs(nodes[0]))
            nodes.forEach { (r, c) -> board[r][c] = id }
        }
        return if (answer == INF) 0 else answer
    }

    private fun bfs(start: Node, end: Node): Int {
        val queue = LinkedList<Node>().apply { offer(start) }
        val visited = Array(board.size) { BooleanArray(board[0].size) }.apply {
            this[start.r][start.c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == end.r && cur.c == end.c) return cur.count

            for ((mr, mc) in moves) {
                var nr = cur.r + mr
                var nc = cur.c + mc

                if (nr !in visited.indices || nc !in visited[0].indices) continue

                if (!visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.count + 1))
                }

                while (board[nr][nc] == 0 && nr + mr in visited.indices && nc + mc in visited[0].indices) {
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

    private data class Node(val r: Int, val c: Int, val count: Int)

    companion object {
        private const val INF = 1001
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

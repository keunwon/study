package algorithm.programmers

import java.util.LinkedList

class Lesson60063_2 {
    fun solution(board: Array<IntArray>): Int {
        val visited = Array(board.size) { Array(board[0].size) { BooleanArray(2) } }
        val q = LinkedList<Node>()
        val dr = intArrayOf(0, 1, 0, -1)
        val dc = intArrayOf(1, 0, -1, 0)

        fun enqueue(p1: Pos, p2: Pos, dist: Int) {
            val top = if (p1.r == p2.r) {
                if (p1.c < p2.c) p1 else p2
            } else {
                if (p1.r < p2.r) p1 else p2
            }
            val dir = if (p1.r == p2.r) 0 else 1

            if (!visited[top.r][top.c][dir]) {
                visited[top.r][top.c][dir] = true
                q.offer(Node(p1, p2, dist))
            }
        }

        fun isValid(r: Int, c: Int): Boolean {
            return r in board.indices && c in board[0].indices && board[r][c] == 0
        }

        enqueue(Pos(0, 0), Pos(0, 1), 0)

        while (q.isNotEmpty()) {
            val cur = q.poll()
            val p1 = cur.p1
            val p2 = cur.p2

            if ((p1.r == board.lastIndex && p1.c == board[0].lastIndex) ||
                (p2.r == board.lastIndex && p2.c == board[0].lastIndex)
            ) {
                return cur.dist
            }

            for (dir in 0 until 4) {
                val nr1 = p1.r + dr[dir]
                val nc1 = p1.c + dc[dir]
                val nr2 = p2.r + dr[dir]
                val nc2 = p2.c + dc[dir]

                if (isValid(nr1, nc1) && isValid(nr2, nc2)) {
                    enqueue(Pos(nr1, nc1), Pos(nr2, nc2), cur.dist + 1)
                }
            }

            if (p1.r == p2.r) {
                for (d in intArrayOf(-1, 1)) {
                    if (isValid(p1.r + d, p1.c) && isValid(p2.r + d, p2.c)) {
                        enqueue(p1, Pos(p1.r + d, p1.c), cur.dist + 1)
                        enqueue(p2, Pos(p2.r + d, p2.c), cur.dist + 1)
                    }
                }
            } else {
                for (d in intArrayOf(-1, 1)) {
                    if (isValid(p1.r, p1.c + d) && isValid(p2.r, p2.c + d)) {
                        enqueue(p1, Pos(p1.r, p1.c + d), cur.dist + 1)
                        enqueue(p2, Pos(p2.r, p2.c + d), cur.dist + 1)
                    }
                }
            }
        }
        return 0
    }

    private data class Pos(val r: Int, val c: Int)

    private data class Node(val p1: Pos, val p2: Pos, val dist: Int)
}

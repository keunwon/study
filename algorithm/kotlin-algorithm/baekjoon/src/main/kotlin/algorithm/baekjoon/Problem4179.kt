package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 불!
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/4179">불! (골드-3)</a>
 **/
class Problem4179 {
    fun solution(board: Array<CharArray>): String {
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        val q = LinkedList<Node>()
        val visited = Array(board.size) { BooleanArray(board[0].size) }
        var start: Node? = null

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 'J') {
                    board[i][j] = '.'
                    visited[i][j] = true
                    start = Node(i, j, type, 1)
                } else if (type == 'F') {
                    q.offer(Node(i, j, type, 1))
                }
            }
        }
        q.offer(start)

        while (q.isNotEmpty()) {
            val cur = q.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in board.indices || nc !in board[0].indices) {
                    if (cur.type == 'J') return "${cur.time}"
                    continue
                }

                if (board[nr][nc] != '.') continue

                val nextNode = Node(nr, nc, cur.type, cur.time + 1)
                if (cur.type == 'F') {
                    board[nr][nc] = 'F'
                    q.offer(nextNode)
                } else if (cur.type == 'J' && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    q.offer(nextNode)
                }
            }
        }
        return "IMPOSSIBLE"
    }

    private class Node(val r: Int, val c: Int, val type: Char, val time: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(n) { br.readLine().toCharArray() }
    Problem4179().solution(board).also { println(it) }
}

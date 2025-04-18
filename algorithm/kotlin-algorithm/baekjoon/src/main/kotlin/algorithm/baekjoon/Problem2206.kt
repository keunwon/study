package algorithm.baekjoon

import java.util.LinkedList

/**
 * <p>
 * 이름: 벽 부수고 이동하기
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2206">벽 부수고 이동하기 (골드-3)</a>
 **/
class Problem2206 {
    fun solution(board: Array<CharArray>): Int {
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        val q = LinkedList<Node>().apply { offer(Node(0, 0, 0, 1)) }
        val visited = Array(board.size) { Array(board[0].size) { BooleanArray(2) } }.apply { this[0][0][0] = true }

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur.r == board.lastIndex && cur.c == board[0].lastIndex) return cur.d

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in board.indices || nc !in board[0].indices) continue

                if (board[nr][nc] == '1' && cur.skill == 0 && !visited[nr][nc][1]) {
                    visited[nr][nc][1] = true
                    q.offer(Node(nr, nc, 1, cur.d + 1))
                } else if (board[nr][nc] == '0' && !visited[nr][nc][cur.skill]) {
                    visited[nr][nc][cur.skill] = true
                    q.offer(Node(nr, nc, cur.skill, cur.d + 1))
                }
            }
        }
        return -1
    }

    private class Node(val r: Int, val c: Int, val skill: Int, val d: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(n) { br.readLine().toCharArray() }

    Problem2206().solution(board).also { println(it) }
    br.close()
}

package algorithm.baekjoon

import java.util.PriorityQueue

/**
 * <p>
 * 이름: 쉬운 최단거리
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14940">쉬운 최단거리 (실버-1)</a>
 **/
class Problem14940 {
    fun solution(board: Array<IntArray>): Array<IntArray> {
        val q = PriorityQueue<Node>(compareBy { it.d })
        val visited = Array(board.size) { BooleanArray(board[0].size) }
        val dist = Array(board.size) { IntArray(board[0].size) { -1 } }

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 0) {
                    dist[i][j] = 0
                } else if (type == 2) {
                    q.offer(Node(i, j, 0))
                    visited[i][j] = true
                    dist[i][j] = 0
                }
            }
        }

        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        while (q.isNotEmpty()) {
            val cur = q.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr in board.indices && nc in board[0].indices && board[nr][nc] == 1 && !visited[nr][nc]) {
                    q.offer(Node(nr, nc, cur.d + 1))
                    dist[nr][nc] = cur.d + 1
                    visited[nr][nc] = true
                }
            }
        }
        return dist
    }

    private class Node(val r: Int, val c: Int, val d: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(m) { arr[it] }
    }

    Problem14940().solution(board).forEach {
        bw.write(it.joinToString(" "))
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}

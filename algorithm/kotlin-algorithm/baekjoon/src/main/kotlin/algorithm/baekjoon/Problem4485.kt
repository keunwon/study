package algorithm.baekjoon

import java.util.PriorityQueue

/**
 * <p>
 * 이름: 녹색 옷 입은 애가 젤다지?
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/4485">녹색 옷 입은 애가 젤다지? (골드-4)</a>
 **/
class Problem4485 {
    fun solution(board: Array<IntArray>): Int {
        val q = PriorityQueue<Node>(compareBy { it.d }).apply { offer(Node(0, 0, 0)) }
        val dist = Array(board.size) { IntArray(board[0].size) { 1e9.toInt() } }.apply { this[0][0] = board[0][0] }
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur.r == board.lastIndex && cur.c == board[0].lastIndex) continue
            if (cur.d > dist[cur.r][cur.c]) continue

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr in board.indices && nc in board[0].indices) {
                    val d = dist[cur.r][cur.c] + board[nr][nc]
                    if (dist[nr][nc] > d) {
                        dist[nr][nc] = d
                        q.offer(Node(nr, nc, d))
                    }
                }
            }
        }
        return dist[board.lastIndex][board[0].lastIndex]
    }

    private class Node(val r: Int, val c: Int, val d: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    var no = 1
    while (true) {
        val n = br.readLine().toInt()
        if (n == 0) break

        val board = Array(n) {
            val arr = br.readLine().split(" ").map { it.toInt() }
            IntArray(n) { arr[it] }
        }
        Problem4485().solution(board).also {
            bw.write("Problem ${no++}: $it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}

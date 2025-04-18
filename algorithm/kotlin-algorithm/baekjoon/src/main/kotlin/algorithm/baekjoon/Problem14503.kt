package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * <p>
 * 이름: 로봇 청소기
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14503">로봇 청소기 (골드-5)</a>
 **/
class Problem14503 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var result = 0

    fun solution(r: Int, c: Int, d: Int, board: Array<IntArray>): Int {
        clean(r, c, d, board)
        return result
    }

    private fun clean(r: Int, c: Int, d: Int, board: Array<IntArray>) {
        if (board[r][c] == 0) {
            board[r][c] = 2
            ++result
        }

        var cd = d
        for (i in moves.indices) {
            val nd = (cd + 3) % 4
            val nr = r + moves[nd].first
            val nc = c + moves[nd].second

            if (nr in board.indices && nc in board[0].indices && board[nr][nc] == 0) {
                clean(nr, nc, nd, board)
                return
            }
            cd = nd
        }

        val nd = (d + 2) % 4
        val nr = r + moves[nd].first
        val nc = c + moves[nd].second

        if (nr in board.indices && nc in board[0].indices && board[nr][nc] != 1) {
            clean(nr, nc, d, board)
        }
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val (r, c, d) = readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    println(Problem14503().solution(r, c, d, board))
}

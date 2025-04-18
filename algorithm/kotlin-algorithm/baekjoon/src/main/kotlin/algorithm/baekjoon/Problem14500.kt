package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * <p>
 * 이름: 테트로미노
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14500">테트로미노 (골드-4)</a>
 **/
class Problem14500 {
    private lateinit var board: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    private var result = 0

    fun solution(board: Array<IntArray>): Int {
        this.board = board
        this.visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            for (j in board[i].indices) {
                dfs(0, i, j, 0)
            }
        }
        return result
    }

    private fun dfs(depth: Int, r: Int, c: Int, sum: Int) {
        if (depth == 4) {
            result = result.coerceAtLeast(sum)
            return
        }

        for ((mr, mc) in moves) {
            val nr = r + mr
            val nc = c + mc

            if (nr in board.indices && nc in board[0].indices && !visited[nr][nc]) {
                if (depth == 2) {
                    visited[nr][nc] = true
                    dfs(depth + 1, r, c, sum + board[nr][nc])
                    visited[nr][nc] = false
                }

                visited[nr][nc] = true
                dfs(depth + 1, nr, nc, sum + board[nr][nc])
                visited[nr][nc] = false
            }
        }
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val st = StringTokenizer(readLine())
        IntArray(m) { st.nextToken().toInt() }
    }

    println(Problem14500().solution(board))
}

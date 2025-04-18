package algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * <p>
 * 이름: 연구소
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14502">연구소 (골드-4)</a>
 **/
class Problem14502 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var result = 0

    fun solution(board: Array<IntArray>): Int {
        dfs(0, board)
        return result
    }

    private fun dfs(depth: Int, board: Array<IntArray>) {
        if (depth == 3) {
            val q = LinkedList<Pair<Int, Int>>()
            val visited = Array(board.size) { BooleanArray(board[0].size) }

            for (i in board.indices) {
                for ((j, type) in board[i].withIndex()) {
                    if (type == 1) {
                        visited[i][j] = true
                    } else if (type == 2) {
                        q.offer(Pair(i, j))
                        visited[i][j] = true
                    }
                }
            }

            while (q.isNotEmpty()) {
                val (r, c) = q.poll()

                for ((mr, mc) in moves) {
                    val nr = r + mr
                    val nc = c + mc

                    if (nr in board.indices &&
                        nc in board[0].indices &&
                        !visited[nr][nc] &&
                        board[nr][nc] == 0
                    ) {
                        visited[nr][nc] = true
                        q.offer(Pair(nr, nc))
                    }
                }
            }

            val safeZones = visited.sumOf { arr -> arr.count { !it } }
            result = result.coerceAtLeast(safeZones)
            return
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == 0) {
                    board[i][j] = 1
                    dfs(depth + 1, board)
                    board[i][j] = 0
                }
            }
        }
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = run {
        val st = StringTokenizer(readLine())
        IntArray(2) { st.nextToken().toInt() }
    }
    val board = Array(n) {
        val st = StringTokenizer(readLine())
        IntArray(m) { st.nextToken().toInt() }
    }

    println(Problem14502().solution(board))
}

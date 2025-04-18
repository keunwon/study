package algorithm.baekjoon

import java.util.*
import kotlin.math.pow

/**
 * <p>
 * 이름:마법사 상어와 파이어스톰
 * 난이도:골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20058">마법사 상어와 파이어스톰 (골드-3)</a>
 **/
class Problem20058 {
    fun solution(board: Array<IntArray>, magics: IntArray): IntArray {
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        for (m in magics) {
            val step = 2.0.pow(m).toInt()
            var tmpBoard = Array(board.size) { IntArray(board[0].size) }

            for (r in board.indices step step) {
                for (c in board[r].indices step step) {
                    for (i in 0 until step) {
                        for (j in 0 until step) {
                            tmpBoard[r + i][c + j] = board[r + step - j - 1][c + i]
                        }
                    }
                }
            }

            for (i in board.indices) {
                System.arraycopy(tmpBoard[i], 0, board[i], 0, board[i].size)
            }

            tmpBoard = Array(board.size) { IntArray(board[0].size) }
            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j] > 0) {
                        val count = moves.count { (mr, mc) ->
                            val nr = i + mr
                            val nc = j + mc
                            nr in board.indices && nc in board[0].indices && board[nr][nc] > 0
                        }

                        tmpBoard[i][j] = board[i][j]
                        if (count < 3) --tmpBoard[i][j]
                    }
                }
            }

            for (i in board.indices) {
                System.arraycopy(tmpBoard[i], 0, board[i], 0, board[0].size)
            }
        }

        var total = 0
        var maxArea = 0
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        while (true) {
            var p: Pair<Int, Int>? = null
            for (i in board.indices) {
                for (j in board[i].indices) {
                    if (board[i][j] > 0 && !visited[i][j]) {
                        p = i to j
                        break
                    }
                }
            }

            if (p == null) break

            val q = LinkedList<Pair<Int, Int>>().apply { offer(p) }
            var area = 0
            visited[p.first][p.second] = true

            while (q.isNotEmpty()) {
                val (r, c) = q.poll()

                total += board[r][c]
                ++area

                for ((mr, mc) in moves) {
                    val nr = r + mr
                    val nc = c + mc

                    if (nr in board.indices && nc in board[0].indices && !visited[nr][nc] && board[nr][nc] > 0) {
                        visited[nr][nc] = true
                        q.offer(nr to nc)
                    }
                }
            }

            maxArea = maxArea.coerceAtLeast(area)
        }

        return intArrayOf(total, maxArea)
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, q) = br.readLine().split(" ").map { it.toInt() }
    val size = 2.0.pow(n).toInt()
    val board = Array(size) {
        val arr = br.readLine().split(" ")
        IntArray(size) { arr[it].toInt() }
    }
    val magic = run {
        val arr = br.readLine().split(" ")
        IntArray(q) { arr[it].toInt() }
    }

    Problem20058().solution(board, magic).forEach { println(it) }
}

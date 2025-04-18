package algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 미세먼지 안녕!
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17144">미세먼지 안녕! (골드-4)</a>
 **/
class Problem17144 {
    fun solution(t: Int, board: Array<IntArray>): Int {
        val airPurifier: Array<Pair<Int, Int>> = run {
            for (i in 0 until board.lastIndex) {
                if (board[i][0] == -1 && board[i + 1][0] == -1) {
                    return@run arrayOf(Pair(i, 0), Pair(i + 1, 0))
                }
            }
            return sumDust(board)
        }

        repeat(t) {
            spreadDust(board)
            onAirPurifier(airPurifier, board)
        }
        return sumDust(board)
    }

    private fun sumDust(board: Array<IntArray>): Int =
        board.sumOf { arr -> arr.sumOf { if (it > 0) it else 0 } }

    private fun onAirPurifier(airPurifier: Array<Pair<Int, Int>>, board: Array<IntArray>) {
        airPurifier[0].let { (r, c) ->
            for (i in r - 1 downTo 1) {
                board[i][c] = board[i - 1][c]
            }

            for (i in 0 until board[0].lastIndex) {
                board[0][i] = board[0][i + 1]
            }

            for (i in 0 until r) {
                board[i][board[0].lastIndex] = board[i + 1][board[0].lastIndex]
            }

            for (i in board[0].lastIndex downTo 2) {
                board[r][i] = board[r][i - 1]
            }
            board[r][1] = 0
        }

        airPurifier[1].let { (r, c) ->
            for (i in r + 1 until board.lastIndex) {
                board[i][c] = board[i + 1][c]
            }

            for (i in 0 until board[0].lastIndex) {
                board[board.lastIndex][i] = board[board.lastIndex][i + 1]
            }

            for (i in board.lastIndex downTo r) {
                board[i][board[0].lastIndex] = board[i - 1][board[0].lastIndex]
            }

            for (i in board[0].lastIndex downTo 2) {
                board[r][i] = board[r][i - 1]
            }
            board[r][1] = 0
        }
    }

    private fun spreadDust(board: Array<IntArray>) {
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        val q = LinkedList<Triple<Int, Int, Int>>().apply {
            for (i in board.indices) {
                for ((j, num) in board[i].withIndex()) {
                    if (num > 0) offer(Triple(i, j, board[i][j]))
                }
            }
        }

        while (q.isNotEmpty()) {
            val (r, c, n) = q.poll()
            val plusNum = n / 5
            var count = 0

            if (plusNum == 0) continue

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr in board.indices && nc in board[0].indices && board[nr][nc] >= 0) {
                    board[nr][nc] += plusNum
                    ++count
                }
            }
            board[r][c] -= plusNum * count
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (r, c, t) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(r) {
        val arr = br.readLine().split(" ")
        IntArray(c) { arr[it].toInt() }
    }

    Problem17144().solution(t, board).also(::println)
}

package algorithm.programmers

import kotlin.math.ceil

class Lesson389478 {
    fun solution(n: Int, w: Int, num: Int): Int {
        val h = ceil(n.toDouble() / w).toInt()
        val board = Array(h) { IntArray(w) }
        var target = 0

        loop@ for (i in board.indices) {
            if (i % 2 == 0) {
                for (j in 0 until w) {
                    if (target >= n) break@loop
                    board[i][j] = ++target
                }
            } else {
                for (j in w - 1 downTo 0) {
                    if (target >= n) break@loop
                    board[i][j] = ++target
                }
            }
        }

        var sr = -1
        var sc = -1
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == num) {
                    sr = i
                    sc = j
                    break
                }
            }
        }

        var result = 0
        for (i in sr until h) {
            if (board[i][sc] != 0) ++result
        }
        return result
    }
}

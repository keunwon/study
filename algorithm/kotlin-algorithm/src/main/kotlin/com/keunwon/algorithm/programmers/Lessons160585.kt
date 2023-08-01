package com.keunwon.algorithm.programmers

/**
 * Title: 혼자서 하는 틱택토
 * Level: 2
 **/
// todo
class Lessons160585 {
    fun solution(board: Array<String>): Int {
        val oCount = board.sumOf { arr -> arr.count { it == 'O' } }
        val xCount = board.sumOf { arr -> arr.count { it == 'X' } }

        val valid = when (oCount) {
            xCount + 1 -> !check(board, 'X')
            xCount -> !check(board, 'O')
            else -> false
        }
        return if (valid) 1 else 0
    }

    private fun check(board: Array<String>, target: Char): Boolean {
        for (arr in board) {
            if (arr.all { it == target }) return true
        }

        for (i in 0 until 3) {
            var count = 0
            for (j in 0 until 3) {
                if (board[j][i] == target) count++
            }
            if (count == 3) return true
        }
        return charArrayOf(board[0][0], board[1][1], board[2][2]).all { it == target } ||
                charArrayOf(board[0][2], board[1][1], board[2][0]).all { it == target }
    }
}

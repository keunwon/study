package com.keunwon.algorithm.programmers

class Lesson160585 {
    fun solution(board: Array<String>): Int {
        val oCount = board.sumOf { arr -> arr.count { it == 'O' } }
        val xCount = board.sumOf { arr -> arr.count { it == 'X' } }
        val valid = when (oCount) {
            xCount -> !check(board, 'O')
            xCount + 1 -> !check(board, 'X')
            else -> false
        }
        return if (valid) 1 else 0
    }

    private fun check(board: Array<String>, target: Char): Boolean {
        for (i in board.indices) {
            if (board[i].all { it == target }) return true
        }

        for (i in board[0].indices) {
            var count = 0
            for (j in board.indices) {
                if (board[j][i] == target) ++count
            }
            if (count == board.size) return true
        }
        return charArrayOf(board[0][0], board[1][1], board[2][2]).all { it == target } ||
                charArrayOf(board[0][2], board[1][1], board[2][0]).all { it == target }
    }
}
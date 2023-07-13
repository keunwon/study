package com.keunwon.algorithm.programmers

/**
 * Title: 혼자서 하는 틱택토
 * Level: 2
 **/
class Lessons160585 {
    fun solution(board: Array<String>): Int {
        val oCount = board.sumOf { arr -> arr.count { it == 'O' } }
        val xCount = board.sumOf { arr -> arr.count { it == 'X' } }

        return when {
            else -> 1
        }
    }
}

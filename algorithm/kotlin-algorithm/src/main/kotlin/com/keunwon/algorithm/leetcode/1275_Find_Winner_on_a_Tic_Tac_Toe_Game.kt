package com.keunwon.algorithm.leetcode

class `1275_Find_Winner_on_a_Tic_Tac_Toe_Game` {
    fun tictactoe(moves: Array<IntArray>): String {
        val map = Array(3) { CharArray(3) { ' ' } }

        for (i in moves.indices) {
            val (r, c) = moves[i]
            val type = if (i % 2 == 0) 'X' else 'O'
            map[r][c] = type
        }

        val aWin = isWin(map, 'X')
        val bWin = isWin(map, 'O')

        return when {
            (aWin && bWin) || (!aWin && !bWin) -> "Draw"
            aWin -> "A"
            else -> "B"
        }
    }

    private fun isWin(map: Array<CharArray>, target: Char): Boolean {
        for (i in map.indices) {
            if (map[i].all { it == target }) return true
        }

        for (i in map[0].indices) {
            var count = 0
            for (j in map.indices) {
                if (map[j][i] == target) ++count
            }
            if (count == map.size) return true
        }

        return charArrayOf(map[0][0], map[1][1], map[2][2]).all { it == target } ||
                charArrayOf(map[0][2], map[1][1], map[2][0]).all { it == target }
    }
}

fun main() {
    //
    `1275_Find_Winner_on_a_Tic_Tac_Toe_Game`()
        .tictactoe(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(1, 1),
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 0),
                intArrayOf(2, 0)
            )
        ).also { println(it) }
}

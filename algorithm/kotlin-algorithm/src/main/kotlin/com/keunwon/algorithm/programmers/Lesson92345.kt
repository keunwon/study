package com.keunwon.algorithm.programmers

import kotlin.math.max
import kotlin.math.min

class Lesson92345 {
    private lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        this.board = board
        return dfs(0, aloc, bloc).count
    }

    private fun dfs(depth: Int, player: IntArray, other: IntArray): Node {
        val (pr, pc) = player
        var isWin = false
        var winCount = board.size * board[0].size
        var loseCount = depth

        if (board[pr][pc] == 1) {
            for ((mr, mc) in moves) {
                val nr = pr + mr
                val nc = pc + mc

                if (nr !in board.indices || nc !in board[0].indices) continue
                if (board[nr][nc] == 0) continue

                board[pr][pc] = 0

                val node = dfs(depth + 1, other, intArrayOf(nr, nc))
                isWin = isWin || !node.isWin
                if (!node.isWin) winCount = min(winCount, node.count)
                else loseCount = max(loseCount, node.count)

                board[pr][pc] = 1
            }
        }
        return Node(isWin, if (isWin) winCount else loseCount)
    }

    private data class Node(val isWin: Boolean, var count: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

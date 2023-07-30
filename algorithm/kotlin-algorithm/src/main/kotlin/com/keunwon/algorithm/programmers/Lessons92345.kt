package com.keunwon.algorithm.programmers

/**
 * Title: 사라지는 발판
 * Level: 3
 **/
class Lessons92345 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        return dfs(0, board, aloc, bloc).count
    }

    private fun dfs(depth: Int, board: Array<IntArray>, player: IntArray, other: IntArray): Node {
        val (pr, pc) = player
        var isWin = false
        var winnerCount = board.size * board[0].size
        var loserCount = depth
        val createNode = { Node(isWin, if (isWin) winnerCount else loserCount) }

        if (board[pr][pc] == 0) return createNode()

        for ((mr, mc) in moves) {
            val nr = pr + mr
            val nc = pc + mc

            if (nr !in board.indices || nc !in board[0].indices) continue
            if (board[nr][nc] == 0) continue

            board[pr][pc] = 0
            val node = dfs(depth + 1, board, other, intArrayOf(nr, nc)).also {
                isWin = isWin || !it.isWin
            }
            if (node.isWin) loserCount = loserCount.coerceAtLeast(node.count)
            else winnerCount = winnerCount.coerceAtMost(node.count)
            board[pr][pc] = 1
        }
        return createNode()
    }

    private data class Node(val isWin: Boolean, val count: Int)
}

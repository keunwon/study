package com.keunwon.algorithm.leetcode

class `130_Surrounded_Regions` {
    private lateinit var board: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>

    fun solve(board: Array<CharArray>): Unit {
        this.board = board
        this.visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            if (board[i][0] == 'O') dfs(i, 0)
        }

        for (i in board.indices) {
            if (board[i][board[0].lastIndex] == 'O') dfs(i, board[0].lastIndex)
        }

        for (i in board[0].indices) {
            if (board[0][i] == 'O') dfs(0, i)
        }

        for (i in board[0].indices) {
            if (board[board.lastIndex][i] == 'O') {
                dfs(board.lastIndex, i)
            }
        }

        for (i in board.indices) {
            for (j in board[0].indices) {
                if (!visited[i][j] && board[i][j] == 'O') board[i][j] = 'X'
            }
        }
    }

    private fun dfs(r: Int, c: Int) {
        visited[r][c] = true

        for ((mr, mc) in moves) {
            val nr = r + mr
            val nc = c + mc

            if (nr in visited.indices && nc in visited[0].indices && !visited[nr][nc] && board[nr][nc] == 'O') {
                visited[r][c] = true
                dfs(nr, nc)
            }
        }
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val result1 = run {
        val board = arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'O', 'X'),
            charArrayOf('X', 'O', 'X', 'X'),
        )
        `130_Surrounded_Regions`().solve(board)
        board
    }
    result1.forEach { println(it.joinToString(", ")) }

    val result2 = run {
        val board = arrayOf(
            charArrayOf('X', 'O', 'X'),
            charArrayOf('O', 'X', 'O'),
            charArrayOf('X', 'O', 'X'),
        )
        `130_Surrounded_Regions`().solve(board)
        board
    }
    result2.forEach { println(it.joinToString(", ")) } // [["X","O","X"],["O","X","O"],["X","O","X"]]
}

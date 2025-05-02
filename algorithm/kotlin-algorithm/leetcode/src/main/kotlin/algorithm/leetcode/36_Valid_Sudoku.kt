package algorithm.leetcode

class `36_Valid_Sudoku` {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (i in board.indices) {
            val visited = BooleanArray(10)
            for (n in board[i]) {
                if (n != '.') {
                    if (visited[n - '0']) return false
                    visited[n - '0'] = true
                }
            }
        }

        for (i in board[0].indices) {
            val visited = BooleanArray(10)
            for (j in board.indices) {
                val c = board[j][i]
                if (c != '.') {
                    if (visited[c - '0']) return false
                    visited[c - '0'] = true
                }
            }
        }

        for (i in board.indices step 3) {
            for (j in board[0].indices step 3) {
                val visited = BooleanArray(10)
                for (k in 0 until 3) {
                    for (l in 0 until 3) {
                        val c = board[i + k][j + l]
                        if (c != '.') {
                            if (visited[c - '0']) return false
                            visited[c - '0'] = true
                        }
                    }
                }
            }
        }
        return true
    }
}

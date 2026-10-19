class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val sudoku = IntArray(27)
        for (i in board.indices) {
            for (j in board[i].indices) {
                val c = board[i][j]
                if (c == '.') continue

                val mask = 1 shl (c - '0')
                val col = 9 + j
                val box = 18 + (i / 3 * 3) + (j / 3)

                if ((sudoku[i] and mask == 0) && (sudoku[col] and mask == 0) && (sudoku[box] and mask == 0)) {
                    sudoku[i] = sudoku[i] or mask
                    sudoku[col] = sudoku[col] or mask
                    sudoku[box] = sudoku[box] or mask
                } else {
                    return false
                }
            }
        }
        return true
    }
}
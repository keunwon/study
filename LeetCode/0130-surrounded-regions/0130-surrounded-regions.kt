class Solution {
    fun solve(board: Array<CharArray>): Unit {
        val q = ArrayDeque<Pair<Int, Int>>()

        for (i in board.indices) {
            if (board[i][0] == 'O') {
                q.addLast(i to 0)
            }
            if (board[i][board[0].lastIndex] == 'O') {
                q.addLast(i to board[0].lastIndex)
            }
        }

        for (i in board[0].indices) {
            if (board[0][i] == 'O') {
                q.addLast(0 to i)
            }

            if (board[board.lastIndex][i] == 'O') {
                q.addLast(board.lastIndex to i)
            }
        }

        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)

        while (!q.isEmpty()) {
            val (r, c) = q.removeFirst()
            board[r][c] = '.'

            for (dir in 0 until 4) {
                val nr = r + dr[dir]
                val nc = c + dc[dir]

                if (nr in board.indices && nc in board[0].indices && board[nr][nc] == 'O') {
                    board[nr][nc] = '.'
                    q.addLast(nr to nc)
                }
            }
        }
        
        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] = if (board[i][j] == '.') 'O' else 'X'
            }
        }
    }
}
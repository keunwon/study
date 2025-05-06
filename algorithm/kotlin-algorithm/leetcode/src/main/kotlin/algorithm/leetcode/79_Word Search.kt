package algorithm.leetcode

class `79_Word Search` {
    private lateinit var board: Array<CharArray>
    private lateinit var visited: Array<BooleanArray>
    private lateinit var word: String

    fun exist(board: Array<CharArray>, word: String): Boolean {
        this.board = board
        this.word = word
        this.visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == word[0] && dfs(0, i, j)) return true
            }
        }
        return false
    }

    private fun dfs(depth: Int, r: Int, c: Int): Boolean {
        if (depth == word.length) return true

        if (r !in visited.indices || c !in visited[0].indices) return false
        if (visited[r][c] || board[r][c] != word[depth]) return false


        visited[r][c] = true
        val result = dfs(depth + 1, r - 1, c) ||
                dfs(depth + 1, r, c + 1) ||
                dfs(depth + 1, r + 1, c) ||
                dfs(depth + 1, r, c - 1)
        visited[r][c] = false
        return result
    }
}

fun main() {
    `79_Word Search`().exist(
        arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E'),
        ),
        "ABCCED"
    ).also { println(it) } // true

    `79_Word Search`().exist(
        arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ),
        "SEE"
    ).also { println(it) } // true

    `79_Word Search`().exist(
        arrayOf(
            charArrayOf('A', 'B', 'C', 'E'),
            charArrayOf('S', 'F', 'C', 'S'),
            charArrayOf('A', 'D', 'E', 'E')
        ),
        "ABCB"
    ).also { println(it) } // false
}

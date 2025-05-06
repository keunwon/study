package algorithm.leetcode

class `0_LeetCode` {
    private lateinit var board: Array<CharArray>
    private lateinit var word: String
    private lateinit var visited: Array<BooleanArray>

    fun exist(board: Array<CharArray>, word: String): Boolean {
        this.board = board
        this.word = word
        this.visited = Array(board.size) { BooleanArray(board[0].size) }

        return true
    }

    private fun dfs(depth: Int, r: Int, c: Int): Boolean {
        if (depth == word.length) return true

        if (r !in board.indices || c !in board[0].indices) return false
        if (visited[r][c] || word[depth] != board[r][c]) return false

        visited[r][c] = true
        val valid = dfs(depth + 1, r - 1, c) ||
                dfs(depth + 1, r, c + 1) ||
                dfs(depth + 1, r + 1, c) ||
                dfs(depth + 1, r, c - 1)
        visited[r][c] = false
        return valid
    }
}

fun main() {
}

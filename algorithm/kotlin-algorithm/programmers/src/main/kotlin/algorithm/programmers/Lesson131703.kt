package algorithm.programmers

class Lesson131703 {
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        val result = dfs(beginning, target, 0, 0)
        return if (result == INF) -1 else result
    }

    private fun dfs(
        beginning: Array<IntArray>,
        target: Array<IntArray>,
        row: Int,
        turnCount: Int,
    ): Int {
        if (row == beginning.size) {
            var total = turnCount
            for (i in beginning[0].indices) {
                val same = beginning.indices.count { beginning[it][i] == target[it][i] }
                if (same == 0) {
                    ++total
                } else if (same != beginning.size) {
                    return 1e9.toInt()
                }
            }
            return total
        }

        var result = INF

        turn(beginning, row)
        result = result.coerceAtMost(dfs(beginning, target, row + 1, turnCount + 1))

        turn(beginning, row)
        result = result.coerceAtMost(dfs(beginning, target, row + 1, turnCount))

        return result
    }

    private fun turn(board: Array<IntArray>, row: Int) {
        for ((i, n) in board[row].withIndex()) {
            board[row][i] = n xor 1
        }
    }

    companion object {
        private const val INF = 1e9.toInt()
    }
}

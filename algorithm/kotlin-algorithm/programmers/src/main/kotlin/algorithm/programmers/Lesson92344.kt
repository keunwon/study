package algorithm.programmers

class Lesson92344 {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val prefixSum = Array(board.size + 1) { IntArray(board[0].size + 1) }

        for (arr in skill) {
            val (type, r1, c1, r2, c2) = arr
            val degree = if (type == 1) -arr.last() else arr.last()

            prefixSum[r1][c1] += degree
            prefixSum[r1][c2 + 1] += -degree
            prefixSum[r2 + 1][c1] += -degree
            prefixSum[r2 + 1][c2 + 1] += degree
        }

        for (i in prefixSum[0].indices) {
            for (j in 1 until prefixSum.size) {
                prefixSum[j][i] += prefixSum[j - 1][i]
            }
        }
        for (i in prefixSum.indices) {
            for (j in 1 until prefixSum[0].size) {
                prefixSum[i][j] += prefixSum[i][j - 1]
            }
        }
        for (i in board.indices) {
            for (j in board[0].indices) {
                board[i][j] += prefixSum[i][j]
            }
        }
        return board.sumOf { arr -> arr.count { it > 0 } }
    }
}

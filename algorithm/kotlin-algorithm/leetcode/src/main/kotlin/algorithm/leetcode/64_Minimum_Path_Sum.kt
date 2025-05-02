package algorithm.leetcode

class `64_Minimum_Path_Sum` {
    fun minPathSum(grid: Array<IntArray>): Int {
        val dp = Array(grid.size) { IntArray(grid[0].size) }.apply { this[0][0] = grid[0][0] }

        for (i in 1 until grid[0].size) {
            dp[0][i] = dp[0][i - 1] + grid[0][i]
        }

        for (i in 1 until grid.size) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        }

        for (i in 1 until dp.size) {
            for (j in 1 until dp[i].size) {
                dp[i][j] = dp[i - 1][j].coerceAtMost(dp[i][j - 1]) + grid[i][j]
            }
        }

        return dp[grid.lastIndex][grid[0].lastIndex]
    }
}

fun main() {
    `64_Minimum_Path_Sum`().minPathSum(
        arrayOf(
            intArrayOf(1, 3, 1),
            intArrayOf(1, 5, 1),
            intArrayOf(4, 2, 1),
        )
    ).also { println(it) } // expect: 7
}

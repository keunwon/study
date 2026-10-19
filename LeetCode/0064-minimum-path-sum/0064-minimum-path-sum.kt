class Solution {
    fun minPathSum(grid: Array<IntArray>): Int {
        val dp = IntArray(grid[0].size + 1) { 1e9.toInt() }
        dp[1] = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                dp[j + 1] = minOf(dp[j], dp[j + 1]) + grid[i][j]
            }
        }
        return dp.last()
    }
}
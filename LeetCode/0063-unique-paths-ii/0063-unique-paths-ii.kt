class Solution {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid[0][0] == 1) {
            return 0
        }

        val dp = IntArray(obstacleGrid[0].size + 1)
        dp[1] = 1

        for (i in obstacleGrid.indices) {
            for (j in obstacleGrid[i].indices) {
                dp[j + 1] = if (obstacleGrid[i][j] == 0) {
                    dp[j] + dp[j + 1]
                } else {
                    0
                }
            }
        }
        return dp.last()
    }
}
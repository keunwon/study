package algorithm.leetcode

class `63_Unique_Paths_II` {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val dp = Array(obstacleGrid.size + 1) { IntArray(obstacleGrid[0].size + 1) }
        dp[0][1] = 1

        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (obstacleGrid[i - 1][j - 1] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        return dp.last().last()
    }
}

fun main() {
    `63_Unique_Paths_II`().uniquePathsWithObstacles(
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 1, 0),
            intArrayOf(0, 0, 0)
        )
    ).also { println(it) }

    `63_Unique_Paths_II`().uniquePathsWithObstacles(
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 0)
        )
    ).also { println(it) }
}

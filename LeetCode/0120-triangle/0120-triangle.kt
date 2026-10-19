class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val dp = triangle.last().toIntArray()
        for (i in triangle.lastIndex - 1 downTo 0) {
            for (j in triangle[i].indices) {
                dp[j] = minOf(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        return dp[0]
    }
}
class Solution {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val dp = Array(matrix.size) { IntArray(matrix[0].size) }
        var max = 0

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == '0') {
                    continue
                }

                if (i > 0 && j > 0) {
                    dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                } else {
                    dp[i][j] = 1
                }
                max = maxOf(max, dp[i][j])
            }
        }
        return max * max
    }
}
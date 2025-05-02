package algorithm.leetcode

class `70_Climbing_Stairs` {
    fun climbStairs(n: Int): Int {
        val dp = IntArray(n + 3) { 1e9.toInt() }.apply {
            this[0] = 0
            this[1] = 1
            this[2] = 2
        }

        for (i in 3..n) {
            dp[i] = dp[i - 2] + dp[i - 1]
        }
        return dp[n]
    }
}

class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { 1e9.toInt() }
        dp[0] = 0

        for (coin in coins) {
            for (i in coin..amount) {
                val idx = i - coin
                if (dp[idx] != 1e9.toInt() && dp[i] > dp[idx] + 1) {
                    dp[i] = dp[idx] + 1
                }
            }
        }
        return if (dp[amount] == 1e9.toInt()) -1 else dp[amount]
    }
}
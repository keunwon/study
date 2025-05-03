package algorithm.leetcode

class `122_Best_Time_to_Buy_and_Sell_Stock_II` {
    fun maxProfit(prices: IntArray): Int {
        val dp = IntArray(prices.size)
        for (i in 1 until prices.size) {
            dp[i] = dp[i - 1].coerceAtLeast(prices[i] - prices[i - 1])
        }
        return dp[prices.lastIndex]
    }
}

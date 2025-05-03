package algorithm.leetcode

class `121_Best_Time_to_Buy_and_Sell_Stock` {
    fun maxProfit(prices: IntArray): Int {
        var buy = prices[0]
        var result = 0

        prices.forEach { p ->
            if (buy > p) {
                buy = p
            } else {
                result = result.coerceAtLeast(p - buy)
            }
        }
        return result
    }
}

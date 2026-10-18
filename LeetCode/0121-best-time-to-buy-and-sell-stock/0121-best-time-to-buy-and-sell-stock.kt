class Solution {
    fun maxProfit(prices: IntArray): Int {
        var min = prices[0]
        var max = 0

        for (p in prices) {
            if (min > p) {
                min = p
            } else {
                max = maxOf(max, p - min)
            }
        }
        return max
    }
}
class Solution {
    fun maxProfit(prices: IntArray): Int {
        var x = prices[0]
        var res = 0

        for (p in prices) {
            if (x > p) {
                x = p
            } else {
                res += p - x
                x = p
            }
        }
        return res
    }
}
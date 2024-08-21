package com.keunwon.algorithm.leetcode

import kotlin.math.max

class `714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee` {
    fun maxProfit(prices: IntArray, fee: Int): Int {
        val dp = intArrayOf(0, -prices[0])

        for (i in 1 until prices.size) {
            val tmp = dp[0]
            dp[0] = max(dp[0], dp[1] + prices[i] - fee)
            dp[1] = max(dp[1], tmp - prices[i])
            println(dp.joinToString(", "))
        }
        return dp[0]
    }
}

fun main() {
    `714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee`().maxProfit(
        intArrayOf(1, 3, 2, 8, 4, 9),
        2
    ).also { println(it) } // 8

    `714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee`().maxProfit(
        intArrayOf(1, 3, 7, 5, 10, 3),
        3,
    ).also { println(it) } // 6
}

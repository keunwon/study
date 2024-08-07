package com.keunwon.algorithm.leetcode

class `152_Maximum_Product_Subarray` {
    fun maxProduct(nums: IntArray): Int {
        var max = 1L
        var min = 1L
        var result = 0L

        for (num in nums.map { it.toLong() }) {
            val tmp = num * max

            max = maxOf(num, max * num, min * num)
            min = minOf(num, min * num, tmp)
            result = maxOf(result, max)
        }
        return result.toInt()
    }
}

fun main() {
    `152_Maximum_Product_Subarray`()
        .maxProduct(intArrayOf(0, 10, 10, 10, 10, 10, 10, 10, 10, 10, -10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0))
        .also { println(it) }
}

package com.keunwon.algorithm.leetcode

class `238_Product_of_Array_Except Self` {
    fun productExceptSelf(nums: IntArray): IntArray {
        var left = IntArray(nums.size) { 1 }
        var right = IntArray(nums.size) { 1 }

        for (i in 1 until left.size) {
            left[i] = left[i - 1] * nums[i - 1]
        }

        for (i in right.lastIndex - 1 downTo 0) {
            right[i] = right[i + 1] * nums[i + 1]
        }
        return IntArray(left.size) { left[it] * right[it] }
    }
}

fun main() {
    `238_Product_of_Array_Except Self`()
        .productExceptSelf(intArrayOf(1, 2, 3, 4))
        .also { println(it.joinToString(" ")) }
}

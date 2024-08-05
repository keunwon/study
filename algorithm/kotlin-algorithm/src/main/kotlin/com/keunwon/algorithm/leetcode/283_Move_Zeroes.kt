package com.keunwon.algorithm.leetcode

class `283_Move_Zeroes` {
    fun moveZeroes(nums: IntArray): Unit {
        var left = 0

        for (right in nums.indices) {
            if (nums[right] != 0) {
                val tmp = nums[right]
                nums[right] = nums[left]
                nums[left] = tmp
                ++left
            }
        }
    }
}

fun main() {
    intArrayOf(0, 1, 0, 3, 12).let {
        `283_Move_Zeroes`().moveZeroes(it)
        println(it.joinToString(", "))
    }

    intArrayOf(0).let {
        `283_Move_Zeroes`().moveZeroes(it)
        println(it.joinToString(", "))
    }
}

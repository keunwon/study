package com.keunwon.algorithm.leetcode

class `153_Find_Minimum_in_Rotated_Sorted_Array` {
    fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = (left + right) / 2

            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return nums[right]
    }
}

fun main() {
    `153_Find_Minimum_in_Rotated_Sorted_Array`().findMin(intArrayOf(3, 4, 5, 1, 2)).also { println(it) }
    `153_Find_Minimum_in_Rotated_Sorted_Array`().findMin(intArrayOf(3, 1, 2)).also { println(it) }
}

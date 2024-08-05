package com.keunwon.algorithm.leetcode

class `35_Search_Insert_Position` {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (nums[mid] == target) return mid
            else if (nums[mid] < target) left = mid + 1
            else right = mid - 1
        }
        return right + 1
    }
}

fun main() {
    `35_Search_Insert_Position`().searchInsert(intArrayOf(1, 3, 5, 6), 2).also { println(it) }
}

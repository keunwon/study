package com.keunwon.algorithm.leetcode

class `33_Search_in_Rotated_Sorted_Array` {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (nums[mid] == target) return mid

            if (nums[left] < nums[mid]) {
                if (nums[mid] > target && nums[left] > target) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            } else {
                if (nums[mid] > target && nums[left] > target) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }
        }
        return -1
    }
}

fun main() {
    `33_Search_in_Rotated_Sorted_Array`()
        .search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0)
        .also { println(it) } // 4

    `33_Search_in_Rotated_Sorted_Array`()
        .search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3)
        .also { println(it) } // -1

    `33_Search_in_Rotated_Sorted_Array`()
        .search(intArrayOf(1), 0)
        .also { println(it) } // -1
}

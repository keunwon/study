package com.keunwon.algorithm.leetcode

class `34_Find_First_and_Last_Position_of_Element_in_Sorted_Array` {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) return intArrayOf(-1, -1)

        val idx1 = binarySearch(nums, target, false)
        val idx2 = binarySearch(nums, target, true)
        return intArrayOf(idx1, idx2)
    }

    private fun binarySearch(nums: IntArray, target: Int, isUpper: Boolean): Int {
        var left = 0
        var right = nums.lastIndex
        var result = -1

        while (left <= right) {
            val mid = (left + right) / 2

            if (nums[mid] < target) {
                ++left
            } else if (nums[mid] > target) {
                --right
            } else {
                result = mid
                if (isUpper) left = mid + 1 else right = mid - 1
            }
        }
        return result
    }
}

fun main() {
    `34_Find_First_and_Last_Position_of_Element_in_Sorted_Array`()
        .searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8)
        .also { println(it.joinToString(", ")) } // [3,4]

    `34_Find_First_and_Last_Position_of_Element_in_Sorted_Array`()
        .searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6)
        .also { println(it.joinToString(", ")) } // [-1,-1]

    `34_Find_First_and_Last_Position_of_Element_in_Sorted_Array`()
        .searchRange(intArrayOf(), 0)
        .also { println(it.joinToString(", ")) } // [-1, -1]
}

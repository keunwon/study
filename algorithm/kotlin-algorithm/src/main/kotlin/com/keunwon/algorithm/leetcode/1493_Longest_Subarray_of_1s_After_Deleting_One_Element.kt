package com.keunwon.algorithm.leetcode

class `1493_Longest_Subarray_of_1s_After_Deleting_One_Element` {
    fun longestSubarray(nums: IntArray): Int {
        var left = 0
        var count = 0

        for (i in nums.indices) {
            if (nums[i] == 0) ++count

            if (count > 1 && nums[left++] == 0) --count
        }
        return nums.lastIndex - left
    }
}

fun main() {
    /*
        `1493_Longest_Subarray_of_1s_After_Deleting_One_Element`()
            .longestSubarray(intArrayOf(1, 1, 0, 1))
            .also { println(it) } // 3
    */

    `1493_Longest_Subarray_of_1s_After_Deleting_One_Element`()
        .longestSubarray(intArrayOf(0, 1, 1, 1, 0, 1, 1, 0, 1))
        .also { println(it) } // 5

    `1493_Longest_Subarray_of_1s_After_Deleting_One_Element`()
        .longestSubarray(intArrayOf(1, 1, 1))
        .also { println(it) } // 2
}

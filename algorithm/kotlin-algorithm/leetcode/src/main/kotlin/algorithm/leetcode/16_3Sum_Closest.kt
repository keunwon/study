package algorithm.leetcode

import kotlin.math.abs

class `16_3Sum_Closest` {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        nums.sort()
        var result = nums[0] + nums[1] + nums.last()

        for (i in nums.indices) {
            var left = i + 1
            var right = nums.lastIndex

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                if (sum < target) ++left else --right
                if (abs(target - sum) < abs(result - target)) result = sum
            }
        }
        return result
    }
}

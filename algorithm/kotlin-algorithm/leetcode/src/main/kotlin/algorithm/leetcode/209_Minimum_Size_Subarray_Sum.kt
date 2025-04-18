package algorithm.leetcode

import kotlin.math.min

class `209_Minimum_Size_Subarray_Sum` {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        if (nums.sum() < target) return 0

        var left = 0
        var sum = 0
        var result = nums.size

        for ((right, num) in nums.withIndex()) {
            sum += num

            while (sum >= target) {
                result = min(result, right - left + 1)
                sum -= nums[left]
                ++left
            }
        }
        return result
    }
}

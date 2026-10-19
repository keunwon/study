class Solution {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var l = 0
        var curSum = 0
        var minLen = nums.size + 1

        for (r in nums.indices) {
            curSum += nums[r]

            while (curSum >= target) {
                minLen = minOf(minLen, r - l + 1)
                curSum -= nums[l++]
            }
        }
        return if (minLen == nums.size + 1) 0 else minLen
    }
}
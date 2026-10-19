class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
        val dp = IntArray(nums.size) { 1 }
        for (i in 1 until nums.size) {
            dp[i] = dp[i - 1] * nums[i - 1]
        }

        var x = 1
        for (i in nums.lastIndex - 1 downTo 0) {
            x *= nums[i + 1]
            dp[i] *= x
        }
        return dp
    }
}
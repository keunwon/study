class Solution {
    fun canJump(nums: IntArray): Boolean {
        var idx = 0

        for (i in nums.indices) {
            if (idx >= i) {
                idx = maxOf(idx, i + nums[i])
            } else {
                break
            }
        }
        return idx >= nums.lastIndex
    }
}
class Solution {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.lastIndex

        while (l <= r) {
            val m = l + (r - l) / 2

            if (nums[m] >= target) {
                r = m - 1
            } else {
                l = m + 1
            }
        }
        return l
    }
}
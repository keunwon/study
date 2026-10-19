class Solution {
    fun findPeakElement(nums: IntArray): Int {
        var l = 0
        var r = nums.lastIndex

        while (l <= r) {
            val m = l + (r - l) / 2

            if (m + 1 < nums.size && nums[m] < nums[m + 1]) {
                l = m + 1
            } else {
                r = m - 1
            }
        }
        return l
    }
}
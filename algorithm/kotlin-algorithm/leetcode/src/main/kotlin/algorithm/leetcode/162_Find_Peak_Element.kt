package algorithm.leetcode

class `162_Find_Peak_Element` {
    fun findPeakElement(nums: IntArray): Int {
        var l = 0
        var r = nums.lastIndex

        while (l < r) {
            val mid = (l + r) / 2
            if (nums[mid] < nums[mid + 1]) l = mid + 1
            else r = mid
        }
        return l
    }
}

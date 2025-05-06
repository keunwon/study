package algorithm.leetcode

class `747_Largest_Number_At_Least_Twice_of_Others` {
    fun dominantIndex(nums: IntArray): Int {
        var max = nums[0]
        var maxIdx = 0

        for ((i, n) in nums.withIndex()) {
            if (max < n) {
                max = n
                maxIdx = i
            }
        }

        for (num in nums) {
            if (num == max || num == 0) continue
            if (max / num < 2) return -1
        }
        return maxIdx
    }
}

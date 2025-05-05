package algorithm.leetcode

class `268_Missing_Number` {
    fun missingNumber(nums: IntArray): Int {
        val total = (nums.size * (nums.size + 1)) / 2
        return total - nums.sum()
    }
}

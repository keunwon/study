package algorithm.leetcode

class `136_Single_Number` {
    fun singleNumber(nums: IntArray): Int {
        return nums.fold(0) { acc, num -> acc xor num }
    }
}

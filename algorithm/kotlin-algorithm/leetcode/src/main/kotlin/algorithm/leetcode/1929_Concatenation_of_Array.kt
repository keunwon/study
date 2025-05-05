package algorithm.leetcode

class `1929_Concatenation_of_Array` {
    fun getConcatenation(nums: IntArray): IntArray {
        return IntArray(nums.size * 2) { nums[it % nums.size] }
    }
}

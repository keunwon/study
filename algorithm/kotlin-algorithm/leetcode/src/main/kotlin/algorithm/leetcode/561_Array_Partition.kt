package algorithm.leetcode

class `561_Array_Partition` {
    fun arrayPairSum(nums: IntArray): Int {
        var result = 0
        nums.sort()
        for (i in nums.indices step 2) {
            result += nums[i]
        }
        return result
    }
}

package algorithm.leetcode

class `905_Sort_Array_By_Parity` {
    fun sortArrayByParity(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        var idx1 = 0
        var idx2 = nums.lastIndex

        for (n in nums) {
            if (n % 2 == 0) {
                result[idx1++] = n
            } else {
                result[idx2--] = n
            }
        }
        return result
    }
}

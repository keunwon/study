package algorithm.leetcode

class `3396_Minimum_Number_of_Operations_to_Make_Elements_in_Array_Distinct` {
    fun minimumOperations(nums: IntArray): Int {
        val countArr = IntArray(101).apply { nums.forEach { ++this[it] } }
        var idx = 0
        var result = 0

        while (idx < nums.size && countArr.any { it > 1 }) {
            val end = (idx + 3).coerceAtMost(nums.size)
            for (i in idx until end) {
                --countArr[nums[i]]
            }
            idx = end
            ++result
        }
        return result
    }
}

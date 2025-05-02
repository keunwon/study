package algorithm.leetcode

class `80_Remove_Duplicates_from_Sorted_Array_II` {
    fun removeDuplicates(nums: IntArray): Int {
        var idx = 0
        var result = 0
        var pre = 0
        var count = 0

        for (n in nums) {
            if (pre == n) {
                if (count < 2) {
                    nums[idx++] = n
                    ++result
                }
                ++count
            } else {
                pre = n
                count = 1
                nums[idx++] = n
                ++result
            }
        }
        return result
    }
}

package algorithm.leetcode

class `26_Remove_Duplicates_from_Sorted_Array` {
    fun removeDuplicates(nums: IntArray): Int {
        var idx = 0
        val set = HashSet<Int>(nums.size)

        for (num in nums) {
            if (!set.contains(num)) {
                set.add(num)
                nums[idx] = num
                ++idx
            }
        }
        return set.size
    }
}

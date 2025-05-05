package algorithm.leetcode

class `217_Contains Duplicate` {
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = HashSet<Int>(nums.size)
        for (num in nums) {
            if (set.contains(num)) return true
            set.add(num)
        }
        return false
    }
}

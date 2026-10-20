class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size < 3) return nums.size

        var idx = 2
        for (i in 2 until nums.size) {
            if (nums[idx - 2] != nums[i]) {
                nums[idx++] = nums[i]
            }
        }
        return idx
    }
}
class Solution {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = hashMapOf<Int, Int>()
        for (i in nums.indices) {
            val num = nums[i]
            val j = map[num]

            if (j != null && k >= i - j) {
                return true
            }
            map[num] = i
        }
        return false
    }
}
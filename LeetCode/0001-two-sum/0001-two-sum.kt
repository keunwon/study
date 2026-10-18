class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val num = nums[i]

            if (map.containsKey(target - num)) {
                return intArrayOf(map.getValue(target - num), i)
            }
            map[num] = i
        }
        return intArrayOf(-1)
    }
}
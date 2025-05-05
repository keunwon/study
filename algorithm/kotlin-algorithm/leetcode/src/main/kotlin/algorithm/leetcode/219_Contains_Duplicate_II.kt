package algorithm.leetcode

import kotlin.math.abs

class `219_Contains_Duplicate_II` {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = HashMap<Int, Int>(nums.size)
        for ((i, num) in nums.withIndex()) {
            if (map.contains(num)) {
                if (abs(i - map.getValue(num)) <= k) return true
            }
            map[num] = i
        }
        return false
    }
}

package algorithm.leetcode

class `594_Longest_Harmonious_Subsequence` {
    fun findLHS(nums: IntArray): Int {
        val map = nums.toList().groupingBy { it }.eachCount()
        var result = 0

        for ((key, value) in map) {
            val findKey = intArrayOf(key - 1, key + 1).maxBy { map.getOrDefault(it, 0) }
            val findValue = map.getOrDefault(findKey, 0)

            if (findValue > 0) result = result.coerceAtLeast(value + findValue)
        }
        return result
    }
}

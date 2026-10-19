class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        val map = hashMapOf<Int, Int>()
        var maxLen = 0

        for (n in nums) {
            if (map.contains(n)) {
                continue
            }

            val left = map[n - 1] ?: 0
            val right = map[n + 1] ?: 0
            val sum = left + right + 1

            maxLen = maxOf(maxLen, sum)
            map[n] = sum
            map[n - left] = sum
            map[n + right] = sum
        }
        return maxLen
    }
}
package algorithm.leetcode

class `3_Longest_Substring_Without_Repeating_Characters` {
    fun lengthOfLongestSubstring(s: String): Int {
        var result = 0
        val map = HashMap<Char, Int>(s.length)
        var sIndex = 0

        for ((i, c) in s.withIndex()) {
            map[c] = map.getOrDefault(c, 0) + 1

            while (map.getValue(c) > 1) {
                val key = s[sIndex]
                map[key] = map.getValue(key) - 1
                ++sIndex
            }
            result = result.coerceAtLeast(i - sIndex + 1)
        }
        return result
    }
}

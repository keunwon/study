class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        val alphabet = IntArray(128) { -1 }
        var endIdx = 0
        var maxLen = 0

        for (i in s.indices) {
            val code = s[i].code

            if (alphabet[code] != -1) {
                endIdx = maxOf(endIdx, alphabet[code] + 1)
            }
            maxLen = maxOf(maxLen, i - endIdx + 1)
            alphabet[code] = i
        }
        return maxLen
    }
}
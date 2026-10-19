class Solution {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isBlank()) return true

        var sIdx = 0
        for (c in t) {
            if (s[sIdx] == c) {
                ++sIdx
                if (sIdx == s.length) return true
            }
        }
        return false
    }
}
package com.keunwon.algorithm.leetcode

class `5_Longest_Palindromic_Substring` {
    fun longestPalindrome(s: String): String {
        var longest = 0
        var result = ""

        for (i in s.indices) {
            var (l, r) = i to i
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                if (r - l + 1 > longest) {
                    result = s.substring(l, r + 1)
                    longest = r - l + 1
                }
                --l
                ++r
            }

            l = i
            r = i + 1
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                if (r - l + 1 > longest) {
                    result = s.substring(l, r + 1)
                    longest = r - l + 1
                }
                --l
                ++r
            }
        }
        return result
    }
}

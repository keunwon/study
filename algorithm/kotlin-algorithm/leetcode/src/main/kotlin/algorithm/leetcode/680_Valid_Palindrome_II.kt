package algorithm.leetcode

class `680_Valid_Palindrome_II` {
    fun validPalindrome(s: String): Boolean {
        var left = 0
        var right = s.lastIndex

        while (left < right) {
            if (s[left] != s[right]) {
                return check(s, left + 1, right) || check(s, left, right - 1)
            }
            ++left
            --right
        }
        return true
    }

    private fun check(str: String, s: Int, e: Int): Boolean {
        var left = s
        var right = e
        while (left < right) {
            if (str[left++] != str[right--]) return false
        }
        return true
    }
}

package algorithm.leetcode

class `125_Valid_Palindrome` {
    fun isPalindrome(s: String): Boolean {
        val str = s.lowercase().replace("[^a-z0-9]".toRegex(), "")
        var l = 0
        var r = str.lastIndex

        while (l <= r) {
            if (str[l++] != str[r--]) return false
        }
        return true
    }
}

fun main() {
    `125_Valid_Palindrome`().isPalindrome("0P").also { println(it) }
}

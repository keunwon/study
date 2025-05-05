package algorithm.leetcode

class `409_Longest_Palindrome` {
    fun longestPalindrome(s: String): Int {
        val alphabet = IntArray(26).apply { s.forEach { ++this[it.code] } }
        var sum = 0

        for (i in alphabet.indices) {
            val tmp = alphabet[i] / 2 * 2
            sum += tmp
            alphabet[i] -= tmp
        }
        val add = if (alphabet.any { it == 1 }) 1 else 0
        return sum + add
    }
}

fun main() {
    `409_Longest_Palindrome`().longestPalindrome("bb")
}

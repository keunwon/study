package algorithm.leetcode

class `242_Valid_Anagram` {
    fun isAnagram(s: String, t: String): Boolean {
        val alphabet = IntArray(26).apply { s.forEach { ++this[it -'a'] } }
        t.forEach { --alphabet[it - 'a'] }
        return alphabet.all { it == 0 }
    }
}

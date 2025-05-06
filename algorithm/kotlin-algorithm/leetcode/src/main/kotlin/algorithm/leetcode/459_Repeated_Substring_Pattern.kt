package algorithm.leetcode

class `459_Repeated_Substring_Pattern` {
    fun repeatedSubstringPattern(s: String): Boolean {
        for (size in 1..s.length / 2) {
            if (s.length % size != 0) continue

            val word = s.substring(0, size).repeat(s.length / size)
            if (word.contentEquals(s)) return true
        }
        return false
    }
}

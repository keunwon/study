class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        val count = IntArray(26)

        s.forEach { ++count[it - 'a'] }
        t.forEach { --count[it - 'a'] }

        return count.all { it == 0}
    }
}
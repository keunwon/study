class Solution {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val count = IntArray(26)
        
        ransomNote.forEach { --count[it - 'a'] }
        magazine.forEach { ++count[it - 'a'] }

        return count.none { it < 0 }
    }
}
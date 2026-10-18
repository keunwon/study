class Solution {
    fun lengthOfLastWord(s: String): Int {
        var idx = s.lastIndex
        while (idx >= 0) {
            if (s[idx] != ' ') break
            --idx
        }
        
        var len = 0
        for (i in idx downTo 0) {
            if (s[i] == ' ') break
            ++len
        }
        return len
    }
}
class Solution {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false;

        val str = "$x"
        var l = 0
        var r = str.lastIndex

        while (l < r) {
            if (str[l++] != str[r--]) {
                return false
            }
        }
        return true
    } 
}
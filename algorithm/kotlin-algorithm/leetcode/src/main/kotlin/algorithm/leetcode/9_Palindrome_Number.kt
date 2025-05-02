package algorithm.leetcode

class `9_Palindrome_Number` {
    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false

        var tmp = x
        var target = 0

        while (tmp > 0) {
            target = target * 10 + tmp % 10
            tmp /= 10
        }
        return x == target
    }
}

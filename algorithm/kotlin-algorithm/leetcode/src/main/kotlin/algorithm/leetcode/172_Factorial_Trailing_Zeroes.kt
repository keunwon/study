package algorithm.leetcode

class `172_Factorial_Trailing_Zeroes` {
    fun trailingZeroes(n: Int): Int {
        var result = 0
        var num = n

        while (num % 5 == 0) {
            ++result
            num /= 5
        }
        return result
    }
}

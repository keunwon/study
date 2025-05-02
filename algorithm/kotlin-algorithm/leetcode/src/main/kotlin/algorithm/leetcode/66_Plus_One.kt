package algorithm.leetcode

class `66_Plus_One` {
    fun plusOne(digits: IntArray): IntArray {
        for (i in digits.lastIndex downTo 0) {
            ++digits[i]
            if (digits[i] < 10) return digits
            digits[i] = 0
        }
        return intArrayOf(1, *digits)
    }
}

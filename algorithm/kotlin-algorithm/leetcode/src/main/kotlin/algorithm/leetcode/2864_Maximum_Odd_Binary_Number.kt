package algorithm.leetcode

class `2864_Maximum_Odd_Binary_Number` {
    fun maximumOddBinaryNumber(s: String): String {
        val count = s.count { it == '1' }
        return buildString(s.length) {
            repeat(count - 1) { append('1') }
            repeat(s.length - count) { append('0') }
            append('1')
        }
    }
}

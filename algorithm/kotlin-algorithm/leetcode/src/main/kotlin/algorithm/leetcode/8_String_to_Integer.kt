package algorithm.leetcode

class `8_String_to_Integer` {
    fun myAtoi(s: String): Int {
        var sIdx = 0
        while (sIdx < s.length && s[sIdx] == ' ') {
            ++sIdx
        }

        if (sIdx >= s.length) return 0

        var result = 0
        val isNegative = s[sIdx] == '-'

        if (s[sIdx] == '-' || s[sIdx] == '+') ++sIdx

        while (sIdx < s.length && s[sIdx].isDigit()) {
            val digit = s[sIdx++] - '0'
            if (isNegative) {
                if (result < (Int.MIN_VALUE + digit) / 10) return Int.MIN_VALUE
                result = result * 10 - digit
            } else {
                if (result > (Int.MAX_VALUE - digit) / 10) return Int.MAX_VALUE
                result = result * 10 + digit
            }
        }
        return result
    }
}

fun main() {
    `8_String_to_Integer`().myAtoi(" -042").also { println(it) }
}

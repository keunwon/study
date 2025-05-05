package algorithm.leetcode

class `504_Base 7` {
    fun convertToBase7(num: Int): String {
        val sb = StringBuilder()
        val isNegative = 0 > num
        var target = if (isNegative) -num else num

        while (target > 0) {
            sb.append(target % 7)
            target /= 7
        }

        if (isNegative) sb.append("-")
        return sb.reverse().toString().ifBlank { "0" }
    }
}

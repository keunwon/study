package algorithm.leetcode

class `168_Excel_Sheet_Column_Title` {
    fun convertToTitle(columnNumber: Int): String {
        var n = columnNumber
        val result = StringBuilder()

        while (n-- > 0) {
            result.append('A' + n % 26)
            n /= 26
        }
        return result.reverse().toString()
    }
}

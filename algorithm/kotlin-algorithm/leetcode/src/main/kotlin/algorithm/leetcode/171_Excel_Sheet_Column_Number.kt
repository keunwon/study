package algorithm.leetcode

class `171_Excel_Sheet_Column_Number` {
    fun titleToNumber(columnTitle: String): Int {
        return columnTitle.fold(0) { acc, c -> acc * 26 + (c - 'A' + 1) }
    }
}

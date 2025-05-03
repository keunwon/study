package algorithm.leetcode

class `119_Pascal's_Triangle_II` {
    fun getRow(rowIndex: Int): List<Int> {
        val result = List(rowIndex) { mutableListOf<Int>() }.apply {
            this[0].add(1)
        }

        for (i in 1 until rowIndex) {
            for (j in 0..i) {
                result[i].add(
                    if (j == 0 || j == i) 1
                    else result[i - 1][j - 1] + result[i - 1][j]
                )
            }
        }
        return result.last()
    }
}

package algorithm.leetcode

class `118_Pascal's_Triangle` {
    fun generate(numRows: Int): List<List<Int>> {
        val result = List(numRows) { mutableListOf<Int>() }.apply {
            this[0].add(1)
        }

        for (i in 1 until numRows) {
            for (j in 0..i) {
                result[i].add(
                    if (j == 0 || j == i) 1
                    else result[i - 1][j - 1] + result[i - 1][j]
                )
            }
        }
        return result
    }
}

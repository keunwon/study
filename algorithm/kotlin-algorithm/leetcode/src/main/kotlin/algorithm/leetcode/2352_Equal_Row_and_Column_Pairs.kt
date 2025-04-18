package algorithm.leetcode

class `2352_Equal_Row_and_Column_Pairs` {
    fun equalPairs(grid: Array<IntArray>): Int {
        val map = mutableMapOf<String, Int>()

        for (arr in grid) {
            val key = arr.joinToString(" ")
            map[key] = map.getOrDefault(key, 0) + 1
        }

        var result = 0
        for (i in grid[0].indices) {
            val key = grid.indices.joinToString(" ") { "${grid[it][i]}" }
            result += map.getOrDefault(key, 0)
        }
        return result
    }
}

fun main() {
    `2352_Equal_Row_and_Column_Pairs`().equalPairs(
        arrayOf(
            intArrayOf(11, 1),
            intArrayOf(1, 11)
        )
    ).also { println(it) } // 2
}

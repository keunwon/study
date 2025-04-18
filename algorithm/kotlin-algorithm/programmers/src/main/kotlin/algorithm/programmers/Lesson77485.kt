package algorithm.programmers

import kotlin.math.min

class Lesson77485 {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val map = Array(rows) { r -> IntArray(columns) { c -> r * columns + c + 1 } }
        return queries.map { rotate(map, it) }.toIntArray()
    }

    private fun rotate(map: Array<IntArray>, query: IntArray): Int {
        val (x1, y1, x2, y2) = query.map { it - 1 }
        val tmp = map[x1][y1]
        var min = tmp

        for (i in x1 until x2) {
            map[i][y1] = map[i + 1][y1].also { min = min(min, it) }
        }

        for (i in y1 until y2) {
            map[x2][i] = map[x2][i + 1].also { min = min(min, it) }
        }

        for (i in x2 downTo x1 + 1) {
            map[i][y2] = map[i - 1][y2].also { min = min(min, it) }
        }

        for (i in y2 downTo y1 + 1) {
            map[x1][i] = map[x1][i - 1].also { min = min(min, it) }
        }
        map[x1][y1 + 1] = tmp
        return min
    }
}

package com.keunwon.algorithm.againresolve

class ALessons77485 {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val map = Array(rows) { r -> IntArray(columns) { c -> r * columns + c + 1 } }
        val answer = IntArray(queries.size)

        for ((i, query) in queries.withIndex()) {
            val (y1, x1, y2, x2) = query.map { it - 1 }
            val start = map[y1][x1]
            var min = start

            for (j in y1 until y2) {
                map[j][x1] = map[j + 1][x1].also { min = min.coerceAtMost(it) }
            }

            for (j in x1 until x2) {
                map[y2][j] = map[y2][j + 1].also { min = min.coerceAtMost(it) }
            }

            for (j in y2 downTo y1 + 1) {
                map[j][x2] = map[j - 1][x2].also { min = min.coerceAtMost(it) }
            }

            for (j in x2 downTo x1 + 1) {
                map[y1][j] = map[y1][j - 1].also { min = min.coerceAtMost(it) }
            }
            map[y1][x1 + 1] = start
            answer[i] = min
        }
        return answer.sortedArray()
    }
}

fun main() {
    ALessons77485().solution(
        6,
        6,
        arrayOf(
            intArrayOf(2, 2, 5, 4),
            intArrayOf(3, 3, 6, 6),
            intArrayOf(5, 1, 6, 3)
        )
    ).also { println(it.contentToString()) }

    ALessons77485().solution(
        3, 3,
        arrayOf(
            intArrayOf(1, 1, 2, 2),
            intArrayOf(1, 2, 2, 3),
            intArrayOf(2, 1, 3, 2),
            intArrayOf(2, 2, 3, 3)
        )
    ).also { println(it.contentToString()) }

    ALessons77485().solution(
        100, 97,
        arrayOf(
            intArrayOf(1, 1, 100, 97)
        )
    ).also { println(it.contentToString()) }
}

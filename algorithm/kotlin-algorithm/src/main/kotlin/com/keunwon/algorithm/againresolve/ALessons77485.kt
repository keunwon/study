package com.keunwon.algorithm.againresolve

class ALessons77485 {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val map = run {
            var num = 1
            Array(rows) { IntArray(columns) { num++ } }
        }
        return queries.map { query ->
            val (r1, c1, r2, c2) = query.map(Int::dec)
            rotate(r1, c1, r2, c2, map)
        }.toIntArray()
    }

    private fun rotate(r1: Int, c1: Int, r2: Int, c2: Int, map: Array<IntArray>): Int {
        val first = map[r1][c1]
        var min = first

        for (i in r1 until r2) {
            map[i][c1] = map[i + 1][c1].also { min = min.coerceAtMost(it) }
        }

        for (i in c1 until c2) {
            map[r2][i] = map[r2][i + 1].also { min = min.coerceAtMost(it) }
        }

        for (i in r2 downTo r1 + 1) {
            map[i][c2] = map[i - 1][c2].also { min = min.coerceAtMost(it) }
        }

        for (i in c2 downTo c1 + 1) {
            map[r1][i] = map[r1][i - 1].also { min = min.coerceAtMost(it) }
        }
        map[r1][c1 + 1] = first
        return min
    }
}

fun main() {
    val rows = 6
    val columns = 6
    val queries = arrayOf(
        intArrayOf(2, 2, 5, 4),
        intArrayOf(3, 3, 6, 6),
        intArrayOf(5, 1, 6, 3),
    )
    ALessons77485().solution(rows, columns, queries).also { println(it.contentToString()) }
}

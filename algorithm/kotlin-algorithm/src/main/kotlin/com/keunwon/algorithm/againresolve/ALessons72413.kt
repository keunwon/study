package com.keunwon.algorithm.againresolve

class ALessons72413 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val map = floydWarshall(n, fares)
        return (1..n).fold(Int.MAX_VALUE) { acc, num ->
            val distance = map[s][num] + map[num][a] + map[num][b]
            acc.coerceAtMost(distance)
        }
    }

    private fun floydWarshall(n: Int, fares: Array<IntArray>): Array<IntArray> {
        val map = Array(n + 1) { IntArray(n + 1) }
        for (i in 1..n) {
            for (j in 1..n) {
                map[i][j] = if (i == j) 0 else 20_000_001
            }
        }
        fares.forEach { (s, e, d) ->
            map[s][e] = d
            map[e][s] = d
        }
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val distance = map[j][i] + map[i][k]
                    map[j][k] = map[j][k].coerceAtMost(distance)
                }
            }
        }
        return map
    }
}

fun main() {
    ALessons72413().solution(
        6,
        4,
        6,
        2,
        arrayOf(
            intArrayOf(4, 1, 10),
            intArrayOf(3, 5, 24),
            intArrayOf(5, 6, 2),
            intArrayOf(3, 1, 41),
            intArrayOf(5, 1, 24),
            intArrayOf(4, 6, 50),
            intArrayOf(2, 4, 66),
            intArrayOf(2, 3, 22),
            intArrayOf(1, 6, 25),
        )
    ).also(::println)

    ALessons72413().solution(
        7,
        3,
        4,
        1,
        arrayOf(
            intArrayOf(5, 7, 9),
            intArrayOf(4, 6, 4),
            intArrayOf(3, 6, 1),
            intArrayOf(3, 2, 3),
            intArrayOf(2, 1, 6)
        )
    ).also(::println)

    ALessons72413().solution(
        6,
        4,
        5,
        6,
        arrayOf(
            intArrayOf(2, 6, 6),
            intArrayOf(6, 3, 7),
            intArrayOf(4, 6, 7),
            intArrayOf(6, 5, 11),
            intArrayOf(2, 5, 12),
            intArrayOf(5, 3, 20),
            intArrayOf(2, 4, 8),
            intArrayOf(4, 3, 9)
        )
    ).also(::println)
}

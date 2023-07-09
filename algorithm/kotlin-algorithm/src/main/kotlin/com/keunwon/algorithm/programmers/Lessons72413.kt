package com.keunwon.algorithm.programmers

/**
 * Title: 합승 택시 요금
 * Level: 3
 **/
class Lessons72413 {
    private lateinit var map: Array<IntArray>

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        floydWarshall(n, fares)
        return (1..n).fold(Int.MAX_VALUE) { acc, idx ->
            val sum = map[s][idx] + map[idx][a] + map[idx][b]
            acc.coerceAtMost(sum)
        }
    }

    private fun floydWarshall(n: Int, fares: Array<IntArray>) {
        map = Array(n + 1) { IntArray(n + 1) }

        for (i in 1..n) {
            for (j in 1..n) {
                if (i == j) continue
                map[i][j] = 20_000_001
            }
        }

        fares.forEach { (a, b, distance) ->
            map[a][b] = distance
            map[b][a] = distance
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val sum = map[j][i] + map[i][k]
                    map[j][k] = map[j][k].coerceAtMost(sum)
                }
            }
        }
    }
}

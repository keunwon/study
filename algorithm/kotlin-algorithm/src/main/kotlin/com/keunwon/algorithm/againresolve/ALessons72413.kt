package com.keunwon.algorithm.againresolve

class ALessons72413 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val dist = createDistArr(n, fares).apply { floydWarshall(n, this) }
        return (1..n).fold(Int.MAX_VALUE) { acc, id ->
            val sum = dist[s][id] + dist[id][a] + dist[id][b]
            acc.coerceAtMost(sum)
        }
    }

    private fun floydWarshall(n: Int, dist: Array<IntArray>) {
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val sum = dist[j][i] + dist[i][k]
                    dist[j][k] = dist[j][k].coerceAtMost(sum)
                }
            }
        }
    }

    private fun createDistArr(n: Int, fares: Array<IntArray>): Array<IntArray> {
        val dist = Array(n + 1) { IntArray(n + 1) { 20_000_001 } }

        repeat(n + 1) { dist[it][it] = 0 }
        fares.forEach { (c, d, f) ->
            dist[c][d] = f
            dist[d][c] = f
        }
        return dist
    }
}

fun main() {
    val (n, s, a, b) = intArrayOf(6, 4, 6, 2)
    val fares = arrayOf(
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

    ALessons72413().solution(n, s, a, b, fares).also { println(it) }
}

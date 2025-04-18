package algorithm.programmers

import kotlin.math.min

class Lesson72413 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val dist = Array(n + 1) { IntArray(n + 1) { 20_00_001 } }.apply {
            fares.forEach { (a, b, d) ->
                this[a][b] = d
                this[b][a] = d
            }
            repeat(n) { this[it + 1][it + 1] = 0 }
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    dist[j][k] = min(dist[j][k], dist[j][i] + dist[i][k])
                }
            }
        }

        return dist[s].withIndex().minOf { (index, d) -> d + dist[index][a] + dist[index][b] }
    }
}

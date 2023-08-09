package com.keunwon.algorithm.baekjoon

/**
 * Title: 파티
 * Level: 골드-3
 **/
class AProblem1238 {
    fun solution(n: Int, x: Int, edges: Array<Triple<Int, Int, Int>>): Int {
        val dist = createDistArray(n, edges).apply { dijkstra(n, this) }
        return (1..n).maxOf { dist[it][x] + dist[x][it] }
    }

    private fun dijkstra(n: Int, dist: Array<IntArray>) {
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val distance = dist[j][i] + dist[i][k]

                    if (dist[j][k] > distance) dist[j][k] = distance
                }
            }
        }
    }

    private fun createDistArray(
        n: Int,
        edges: Array<Triple<Int, Int, Int>>,
    ): Array<IntArray> {
        val dist = Array(n + 1) { IntArray(n + 1) { 1e9.toInt() } }

        repeat(n + 1) { dist[it][it] = 0 }
        edges.forEach { (a, b, c) ->
            dist[a][b] = dist[a][b].coerceAtMost(c)
        }
        return dist
    }
}

fun main() {
    val (n, m, x) = readLine()!!.split(" ").map { it.toInt() }
    val edges = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    AProblem1238().solution(n, x, edges).also(::println)
}

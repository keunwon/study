package com.keunwon.algorithm.baekjoon

/**
 * Title: 운동
 * Level: 골드-4
 **/
class Problem1956 {
    fun solution(v: Int, edges: Array<Triple<Int, Int, Int>>): Int {
        val dist = createDistArray(v, edges).apply { dijkstra(v, this) }
        return shortestDistance(v, dist)
    }

    private fun shortestDistance(v: Int, dist: Array<IntArray>): Int {
        var min = INF

        for (i in 1..v) {
            for (j in 1..v) {
                if (i == j || dist[i][j] == INF || dist[j][i] == INF) continue

                min = min.coerceAtMost(dist[i][j] + dist[j][i])
            }
        }
        return min.takeUnless { it == INF } ?: -1
    }

    private fun dijkstra(v: Int, dist: Array<IntArray>) {
        for (i in 1..v) {
            for (j in 1..v) {
                for (k in 1..v) {
                    val distance = dist[j][i] + dist[i][k]

                    if (dist[j][k] > distance) dist[j][k] = distance
                }
            }
        }
    }

    private fun createDistArray(v: Int, edges: Array<Triple<Int, Int, Int>>): Array<IntArray> {
        val dist = Array(v + 1) { IntArray(v + 1) { INF } }

        repeat(v + 1) { dist[it][it] = 0 }
        edges.forEach { (a, b, c) -> dist[a][b] = c }
        return dist
    }

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val (v, e) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(e) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1956().solution(v, arr).also(::println)
}

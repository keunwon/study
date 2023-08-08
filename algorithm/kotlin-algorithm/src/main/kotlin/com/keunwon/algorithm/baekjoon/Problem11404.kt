package com.keunwon.algorithm.baekjoon

/**
 * Title: 플로이드
 * Level: 골드-4
 **/
class Problem11404 {
    fun solution(n: Int, edges: Array<Triple<Int, Int, Int>>): Array<IntArray> {
        val dist = createDistArray(n, edges).apply {
            dijkstra(n, this)
            transferINF(n, this)
        }
        return (1..n).map { dist[it].slice(1..n).toIntArray() }.toTypedArray()
    }

    private fun createDistArray(n: Int, edges: Array<Triple<Int, Int, Int>>): Array<IntArray> {
        val dist = Array(n + 1) { IntArray(n + 1) { INF } }
        repeat(n) { dist[it + 1][it + 1] = 0 }
        edges.forEach { (a, b, c) -> dist[a][b] = dist[a][b].coerceAtMost(c) }
        return dist
    }

    private fun transferINF(n: Int, dist: Array<IntArray>) {
        for (i in 1..n) {
            for (j in 1..n) {
                if (dist[i][j] == INF) dist[i][j] = 0
            }
        }
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

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val edges = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem11404().solution(n, edges).forEach {
        println(it.joinToString(" "))
    }
}

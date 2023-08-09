package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 끝나지 않는 파티
 * Level: 골드-5
 **/
class Problem11265 {
    fun solution(n: Int, parties: Array<IntArray>, edges: Array<Triple<Int, Int, Int>>): Array<String> {
        val dist = createDistArray(n, parties).apply { dijkstra(n, this) }
        return guidMessages(edges, dist)
    }

    private fun guidMessages(edges: Array<Triple<Int, Int, Int>>, dist: Array<IntArray>): Array<String> {
        return edges.map { edge ->
            val valid = dist[edge.first][edge.second] <= edge.third
            if (valid) "Enjoy other party" else "Stay here"
        }.toTypedArray()
    }

    private fun createDistArray(n: Int, parties: Array<IntArray>): Array<IntArray> {
        val dist = Array(n + 1) { IntArray(n + 1) { INF } }

        for (i in parties.indices) {
            for (j in parties[i].indices) {
                dist[i + 1][j + 1] = dist[i + 1][j + 1].coerceAtMost(parties[i][j])
            }
        }
        return dist
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
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val roads = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(n) { st.nextToken().toInt() }
    }
    val edges = Array(m) {
        val st = StringTokenizer(readLine()!!)
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    Problem11265().solution(n, roads, edges).forEach(::println)
}

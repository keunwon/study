package com.keunwon.algorithm.baekjoon

/**
 * Title: 택배
 * Level: 골드-3
 **/
class Problem1719 {
    fun solution(n: Int, edges: Array<Triple<Int, Int, Int>>): Array<Array<String>> {
        val dist = createDistArray(n, edges)
        val table = createTable(n).apply { dijkstra(n, dist, this) }

        return roadTable(n, table)
    }

    private fun roadTable(n: Int, table: Array<IntArray>): Array<Array<String>> {
        val answer = Array(n) { Array(n) { "-" } }
        for (i in 1..n) {
            for (j in 1..n) {
                if (i == j) continue
                answer[i - 1][j - 1] = "${table[i][j]}"
            }
        }
        return answer
    }

    private fun dijkstra(n: Int, dist: Array<IntArray>, table: Array<IntArray>) {
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val distance = dist[j][i] + dist[i][k]

                    if (dist[j][k] > distance) {
                        table[j][k] = table[j][i]
                        dist[j][k] = distance
                    }
                }
            }
        }
    }

    private fun createTable(n: Int): Array<IntArray> {
        val table = Array(n + 1) { IntArray(n + 1) }

        for (i in 1..n) {
            for (j in 1..n) table[i][j] = j
        }
        return table
    }

    private fun createDistArray(n: Int, edges: Array<Triple<Int, Int, Int>>): Array<IntArray> {
        val dist = Array(n + 1) { IntArray(n + 1) { INF } }
        repeat(n + 1) { dist[it][it] = 0 }
        edges.forEach { (a, b, c) ->
            dist[a][b] = c
            dist[b][a] = c
        }
        return dist
    }

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val edges = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1719().solution(n, edges).forEach { println(it.joinToString(" ")) }
}

package com.keunwon.algorithm.baekjoon

/**
 * Title: 타임머신
 * Level: 골드-4
 **/
class Problem11657 {
    private lateinit var nodes: List<Node>
    private lateinit var dist: LongArray

    fun solution(n: Int, edges: Array<IntArray>): List<Long> {
        this.nodes = edges.map { (a, b, c) -> Node(a, b, c) }
        this.dist = LongArray(n + 1) { INF }

        if (!bellmanFord(1, n)) return listOf(-1)
        return (2..n).map { if (dist[it] == INF) -1 else dist[it] }
    }

    private fun bellmanFord(start: Int, n: Int): Boolean {
        dist[start] = 0

        for (i in 1..n) {
            for (node in nodes) {
                if (dist[node.start] == INF) continue

                val distance = dist[node.start] + node.distance

                if (dist[node.end] > distance) {
                    dist[node.end] = distance

                    if (i == n) return false
                }
            }
        }
        return true
    }

    private data class Node(val start: Int, val end: Int, val distance: Int)

    companion object {
        private const val INF = 1e9.toLong()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val edges = Array(m) {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem11657().solution(n, edges).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}

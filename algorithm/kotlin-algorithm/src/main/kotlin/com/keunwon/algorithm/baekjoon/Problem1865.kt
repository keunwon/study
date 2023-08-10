package com.keunwon.algorithm.baekjoon

/**
 * Title: 웜홀
 * Level: 골드-3
 **/
class Problem1865 {
    private lateinit var nodes: List<Node>
    private lateinit var dist: IntArray

    fun solution(n: Int, edges: Array<IntArray>, wormholes: Array<IntArray>): String {
        this.nodes = createNodes(edges, wormholes)
        this.dist = IntArray(n + 1) { INF }

        return if (bellmanFord(n)) "YES" else "NO"
    }

    private fun bellmanFord(n: Int): Boolean {
        for (i in 1..n) {
            var update = false

            for (node in nodes) {
                val distance = dist[node.start] + node.distance

                if (dist[node.end] > distance) {
                    dist[node.end] = distance
                    update = true
                    if (i == n) return true
                }
            }
            if (!update) break
        }
        return false
    }

    private fun createNodes(edges: Array<IntArray>, wormholes: Array<IntArray>): List<Node> {
        val list = mutableListOf<Node>()

        edges.forEach { (a, b, c) ->
            list.add(Node(a, b, c))
            list.add(Node(b, a, c))
        }
        wormholes.forEach { (a, b, c) -> list.add(Node(a, b, -c)) }
        return list
    }

    private data class Node(val start: Int, val end: Int, val distance: Int)

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val readIntArray = {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    repeat(readLine().toInt()) {
        val (n, m, w) = readIntArray()
        val edges = Array(m) { readIntArray() }
        val wormholes = Array(w) { readIntArray() }

        Problem1865().solution(n, edges, wormholes).also {
            bw.write(it)
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}

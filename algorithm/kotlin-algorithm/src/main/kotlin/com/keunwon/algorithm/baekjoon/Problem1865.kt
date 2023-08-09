package com.keunwon.algorithm.baekjoon

/**
 * Title: 웜홀
 * Level: 골드-3
 **/
// todo
class Problem1865 {
    fun solution(
        n: Int,
        edges: Array<Triple<Int, Int, Int>>,
        wormholes: Array<Triple<Int, Int, Int>>,
    ): String {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            edges.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
            wormholes.forEach { (a, b, c) ->
                this[a].add(Node(b, -c))
            }
        }
        val dist = IntArray(n + 1) { INF }


        return ""
    }

    private fun bellmanFord(start: Int, n: Int, graph: Array<MutableList<Node>>, dist: IntArray): Boolean {
        dist[start] = 0

        for (i in 1 until n) {

        }

        return false
    }

    private data class Node(val index: Int, val distance: Int)

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val tc = readLine()!!.toInt()

    repeat(tc) {
        val (n, m, w) = readLine()!!.split(" ").map { it.toInt() }
        val edges = Array(m) {
            readLine()!!.split(" ")
                .map { it.toInt() }
                .let { (a, b, c) -> Triple(a, b, c) }
        }
        val wormholes = Array(w) {
            readLine()!!.split(" ")
                .map { it.toInt() }
                .let { (a, b, c) -> Triple(a, b, c) }
        }

        Problem1865().solution(n, edges, wormholes).also(::println)
    }
}

package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1238 {
    fun solution(n: Int, x: Int, edges: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Node>() }
        val reverseGraph = Array(n + 1) { mutableListOf<Node>() }

        edges.forEach { (a, b, c) ->
            graph[a].add(Node(b, c))
            reverseGraph[b].add(Node(a, c))
        }
        return dijkstra(n, x, graph).zip(dijkstra(n, x, reverseGraph))
            .slice(1..n)
            .maxOf { it.first + it.second }
    }

    private fun dijkstra(n: Int, x: Int, graph: Array<MutableList<Node>>): IntArray {
        val queue = PriorityQueue<Node>(compareBy { it.d }).apply { offer(Node(x, 0)) }
        val dist = IntArray(n + 1) { 1e9.toInt() }.apply { this[x] = 0 }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur.index]) {
                val d = cur.d + next.d
                if (dist[next.index] > d) {
                    dist[next.index] = d
                    queue.offer(Node(next.index, d))
                }
            }
        }
        return dist
    }

    private data class Node(val index: Int, val d: Int)
}

fun main() {
    val (n, m, x) = readln().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val st = StringTokenizer(readln())
        IntArray(3) { st.nextToken().toInt() }
    }

    println(Problem1238().solution(n, x, edges))
}

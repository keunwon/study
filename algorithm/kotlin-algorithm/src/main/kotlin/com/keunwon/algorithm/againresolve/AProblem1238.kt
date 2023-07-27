package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem1238 {
    fun solution(m: Int, x: Int, roads: Array<Triple<Int, Int, Int>>): Int {
        val graph = Array(m + 1) { mutableListOf<Node>() }
        val reversedGraph = Array(m + 1) { mutableListOf<Node>() }

        roads.forEach { (a, b, c) ->
            graph[a].add(Node(b, c))
            reversedGraph[b].add(Node(a, c))
        }
        return dijkstra(graph, x).zip(dijkstra(reversedGraph, x)).maxOf { (a, b) -> a + b }
    }

    private fun dijkstra(graph: Array<MutableList<Node>>, x: Int): IntArray {
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val dp = IntArray(graph.size + 1) { 2e9.toInt() }

        queue.offer(Node(x, 0))
        dp[x] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur.index]) {
                val distance = dp[cur.index] + next.distance
                if (dp[next.index] > distance) {
                    dp[next.index] = distance
                    queue.offer(Node(next.index, distance))
                }
            }
        }
        return dp.sliceArray(1 until dp.size)
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    val (n, m, x) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(m) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }
    AProblem1238().solution(m, x, arr).also { println(it) }
}

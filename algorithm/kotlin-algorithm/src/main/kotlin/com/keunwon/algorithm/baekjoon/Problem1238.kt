package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 파티
 * Level: 골드-3
 **/
class Problem1238 {
    fun solution(n: Int, x: Int, roads: Array<Triple<Int, Int, Int>>): Int {
        val graph = Array(n + 1) { mutableListOf<Node>() }
        val reverseGraph = Array(n + 1) { mutableListOf<Node>() }

        roads.forEach { (a, b, c) ->
            graph[a].add(Node(b, c))
            reverseGraph[b].add(Node(a, c))
        }
        return dijkstra(graph, x).zip(dijkstra(reverseGraph, x))
            .slice(1..n)
            .maxOf { (a, b) -> a + b }
    }

    private fun dijkstra(graph: Array<MutableList<Node>>, x: Int): IntArray {
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val dp = IntArray(graph.size) { 10_000_001 }

        queue.offer(Node(x, 0))
        dp[x] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (next in graph[cur.idx]) {
                val distance = dp[cur.idx] + next.distance
                if (dp[next.idx] > distance) {
                    dp[next.idx] = distance
                    queue.offer(Node(next.idx, distance))
                }
            }
        }
        return dp
    }

    private data class Node(val idx: Int, val distance: Int)
}

fun main() {
    val (n, m, x) = readLine()!!.split(" ").map { it.toInt() }
    val roads = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1238().solution(n, x, roads).also { println(it) }
}

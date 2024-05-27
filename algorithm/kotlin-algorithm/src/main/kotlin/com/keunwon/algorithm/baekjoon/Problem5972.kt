package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem5972 {
    fun solution(n: Int, edges: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            edges.forEach { (s, e, d) ->
                this[s].add(Node(e, d))
                this[e].add(Node(s, d))
            }
        }
        val dist = IntArray(n + 1) { 1e9.toInt() }
        val queue = PriorityQueue<Node>(compareBy { it.distance }).apply {
            offer(Node(1, 0))
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dist[cur.index] < cur.distance) continue
            if (cur.index == n) continue

            for (next in graph[cur.index]) {
                val d = cur.distance + next.distance

                if (dist[next.index] > d) {
                    dist[next.index] = d
                    queue.offer(Node(next.index, d))
                }
            }
        }
        return dist[n]
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val st = StringTokenizer(readln())
        IntArray(3) { st.nextToken().toInt() }
    }

    println(Problem5972().solution(n, edges))
}

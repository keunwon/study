package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons12978 {
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val graph = Array(N + 1) { mutableListOf<Node>() }.apply {
            road.forEach { (s, e, d) ->
                this[s].add(Node(e, d))
                this[e].add(Node(s, d))
            }
        }
        val dist = dijkstra(N, graph)
        return dist.count { it <= k }
    }

    private fun dijkstra(N: Int, graph: Array<MutableList<Node>>): IntArray {
        val queue = LinkedList<Node>().apply { offer(Node(1, 0)) }
        val dist = IntArray(N + 1) { Int.MAX_VALUE }.apply { this[1] = 1 }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (node in graph[cur.index]) {
                val distance = cur.distance + node.distance
                if (distance < dist[node.index]) {
                    dist[node.index] = distance
                    queue.offer(Node(node.index, distance))
                }
            }
        }
        return dist
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    ALessons12978().solution(
        5,
        arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(5, 2, 2),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 4, 2)
        ),
        3
    ).also(::println)

    ALessons12978().solution(
        6,
        arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 2),
            intArrayOf(3, 4, 3),
            intArrayOf(3, 5, 2),
            intArrayOf(3, 5, 3),
            intArrayOf(5, 6, 1)
        ),
        4
    ).also(::println)
}

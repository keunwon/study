package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons132266 {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            roads.forEach { (s, e) ->
                this[s].add(e)
                this[e].add(s)
            }
        }
        val dist = dijkstra(destination, graph)
        return sources.map { dist[it] }.toIntArray()
    }

    private fun dijkstra(start: Int, graph: Array<MutableList<Int>>): IntArray {
        val queue = LinkedList<Int>().apply { offer(start) }
        val dist = IntArray(graph.size) { Int.MAX_VALUE }.apply { set(start, 0) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                val nextDistance = dist[cur] + 1
                if (nextDistance < dist[next]) {
                    dist[next] = nextDistance
                    queue.offer(next)
                }
            }
        }
        return dist.onEachIndexed { i, d -> if (d == Int.MAX_VALUE) dist[i] = -1 }
    }
}

fun main() {
    ALessons132266().solution(
        3,
        arrayOf(intArrayOf(1, 2), intArrayOf(2, 3)),
        intArrayOf(2, 3),
        1
    ).also { println(it.contentToString()) }

    ALessons132266().solution(
        5,
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(4, 5)
        ),
        intArrayOf(1, 3, 5),
        5
    ).also { println(it.contentToString()) }
}

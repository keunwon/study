package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons49189 {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            edge.forEach { (s, e) ->
                this[s].add(e)
                this[e].add(s)
            }
        }
        val dist = IntArray(n + 1) { Int.MAX_VALUE }.apply {
            this[0] = 0
            this[1] = 1
        }
        val queue = LinkedList<Int>().apply { offer(1) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (node in graph[cur]) {
                if (dist[cur] + 1 < dist[node]) {
                    dist[node] = dist[cur] + 1
                    queue.offer(node)
                }
            }
        }
        return dist.count { it == dist.maxOrNull() }
    }
}

fun main() {
    ALessons49189().solution(
        6,
        arrayOf(
            intArrayOf(3, 6),
            intArrayOf(4, 3),
            intArrayOf(3, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 2),
            intArrayOf(2, 4),
            intArrayOf(5, 2)
        )
    ).also(::println)
}

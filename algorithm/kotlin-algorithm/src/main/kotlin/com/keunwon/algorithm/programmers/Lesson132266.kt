package com.keunwon.algorithm.programmers

import java.util.*

class Lesson132266 {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            roads.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        val queue = LinkedList<Int>().apply { offer(destination) }
        val dist = IntArray(n + 1) { 1e9.toInt() }.apply { this[destination] = 0 }


        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                val d = dist[cur] + 1
                if (dist[next] > d) {
                    dist[next] = d
                    queue.offer(next)
                }
            }
        }
        return sources.map { if (dist[it] == 1e9.toInt()) -1 else dist[it] }.toIntArray()
    }
}
package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 부대복귀
 * Level: 3
 **/
class Lessons132266 {
    private lateinit var graph: Array<MutableList<Int>>

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            roads.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }

        val distances = dijkstra(destination)
        return sources.map { distances[it] }.toIntArray()
    }

    private fun dijkstra(destination: Int): IntArray {
        val queue = LinkedList<Int>().apply { offer(destination) }
        val dp = IntArray(graph.size) { Int.MAX_VALUE }.apply { set(destination, 0) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                if (dp[cur] + 1 < dp[next]) {
                    dp[next] = dp[cur] + 1
                    queue.offer(next)
                }
            }
        }
        return dp.onEachIndexed { index, num -> if (num == Int.MAX_VALUE) dp[index] = -1 }
    }
}

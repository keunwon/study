package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons118669 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            paths.forEach { (i, j, w) ->
                this[i].add(Node(j, w))
                this[j].add(Node(i, w))
            }
        }
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val dp = IntArray(n + 1) { INF }

        gates.forEach { gate ->
            queue.offer(Node(gate, 0))
            dp[gate] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dp[cur.index] < cur.distance) continue
            if (cur.index in summits) continue

            for (next in graph[cur.index]) {
                val distance = cur.distance.coerceAtLeast(next.distance)
                if (dp[next.index] > distance) {
                    dp[next.index] = distance
                    queue.offer(Node(next.index, distance))
                }
            }
        }
        return summits.map { intArrayOf(it, dp[it]) }
            .sortedWith(compareBy({ it[1] }, { it[0] }))
            .first()
    }

    private data class Node(val index: Int, val distance: Int)

    companion object {
        private const val INF = 10_000_001
    }
}

fun main() {
    val n = 6
    val paths = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(2, 3, 5),
        intArrayOf(2, 4, 2),
        intArrayOf(2, 5, 4),
        intArrayOf(3, 4, 4),
        intArrayOf(4, 5, 3),
        intArrayOf(4, 6, 1),
        intArrayOf(5, 6, 1),
    )
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(5)

    ALessons118669().solution(n, paths, gates, summits).also { println(it.joinToString(", ")) }
}

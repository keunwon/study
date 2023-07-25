package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 등산코스 정하기
 * Level: 3
 **/
class Lessons118669 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val graph = Array(n + 1) { mutableListOf<Node>() }.also { arr ->
            paths.forEach { (a, b, d) ->
                arr[a].add(Node(b, d))
                arr[b].add(Node(a, d))
            }
        }
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val dp = IntArray(n + 1) { 2e9.toInt() }

        gates.forEach { gate ->
            queue.offer(Node(gate, 0))
            dp[gate] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dp[cur.index] < cur.distance) continue
            if (summits.contains(cur.index)) continue

            for (next in graph[cur.index]) {
                val distance = cur.distance.coerceAtLeast(next.distance)
                if (dp[next.index] > distance) {
                    dp[next.index] = distance
                    queue.offer(Node(next.index, distance))
                }
            }
        }
        return summits.map { it to dp[it] }
            .sortedWith(compareBy({ it.second }, { it.first }))
            .first()
            .let { (a, b) -> intArrayOf(a, b) }
    }

    private data class Node(val index: Int, val distance: Int)
}

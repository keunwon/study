package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 배달
 * Level: 2
 **/
class Lessons12978 {
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val graph = Array(N + 1) { mutableListOf<Node>() }.apply {
            road.forEach { (l, r, distance) ->
                this[l].add(Node(r, distance))
                this[r].add(Node(l, distance))
            }
        }
        val queue = LinkedList<Node>().apply { offer(Node(1, 0)) }
        val dp = IntArray(N + 1) { Int.MAX_VALUE }.apply {
            set(0, 0)
            set(1, 0)
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (node in graph[cur.idx]) {
                if (dp[cur.idx] + node.distance < dp[node.idx]) {
                    dp[node.idx] = dp[cur.idx] + node.distance
                    queue.offer(node)
                }
            }
        }
        return dp.count { it <= k } - 1
    }

    data class Node(val idx: Int, val distance: Int)
}

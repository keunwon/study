package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 가장 먼 노드
 * Level: 3
 **/
class Lessons49189 {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            edge.forEach { (l, r) -> this[l].add(r).also { this[r].add(l) } }
        }
        val queue = LinkedList<Int>().apply { offer(1) }
        val dp = IntArray(n + 1) { Int.MAX_VALUE }.apply {
            set(0, 0)
            set(1, 0)
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (num in graph[cur]) {
                if (dp[cur] + 1 < dp[num]) {
                    dp[num] = dp[cur] + 1
                    queue.offer(num)
                }
            }
        }
        return dp.count { num -> num == dp.maxOf { it } }
    }
}

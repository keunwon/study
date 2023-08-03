package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 택배 배송
 * Level: 골드-5
 **/
class Problem5972 {
    fun solution(n: Int, arr: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            arr.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        val queue = PriorityQueue<Node>(compareBy { it.cost })
            .apply { offer(Node(1, 0)) }
        val dp = IntArray(n + 1) { 50_000_001 }
            .apply { this[1] = 0 }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dp[cur.index] < cur.cost) continue

            for (next in graph[cur.index]) {
                val cost = cur.cost + next.cost

                if (dp[next.index] > cost) {
                    dp[next.index] = cost
                    queue.offer(Node(next.index, cost))
                }
            }
        }
        return dp[n]
    }

    private data class Node(val index: Int, val cost: Int)
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(m) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }.toIntArray()
    }
    Problem5972().solution(n, arr).also(::println)
}

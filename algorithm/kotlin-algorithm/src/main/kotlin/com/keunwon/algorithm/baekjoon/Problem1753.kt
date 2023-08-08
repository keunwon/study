package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 최단 경로
 * Level: 1753
 **/
class Problem1753 {
    fun solution(v: Int, start: Int, edges: Array<Triple<Int, Int, Int>>): Array<String> {
        val graph = Array(v + 1) { mutableListOf<Node>() }.apply {
            edges.forEach { (u, v, w) -> this[u].add(Node(v, w)) }
        }
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val dp = IntArray(v + 1) { INF }

        queue.offer(Node(start, 0))
        dp[start] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dp[cur.index] < cur.distance) continue

            for (next in graph[cur.index]) {
                val distance = cur.distance + next.distance

                if (dp[next.index] > distance) {
                    dp[next.index] = distance
                    queue.offer(Node(next.index, distance))
                }
            }
        }
        return (1 until dp.size).map { if (dp[it] == INF) "INF" else "${dp[it]}" }.toTypedArray()
    }

    private data class Node(val index: Int, val distance: Int)

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val (v, e) = readLine()!!.split(" ").map { it.toInt() }
    val start = readLine()!!.toInt()
    val edges = Array(e) {
        val st = StringTokenizer(readLine()!!)
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    Problem1753().solution(v, start, edges).forEach(::println)
}

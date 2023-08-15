package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 중량제한
 * Level: 골드-3
 **/
class Problem1939 {
    private lateinit var graph: Array<MutableList<Node>>
    private lateinit var position: Pair<Int, Int>

    fun solution(n: Int, edges: Array<Triple<Int, Int, Int>>, position: Pair<Int, Int>): Int {
        this.graph = Array(n + 1) { mutableListOf() }
        this.position = position
        var left = Int.MAX_VALUE
        var right = 0

        edges.forEach { (a, b, c) ->
            graph[a].add(Node(b, c))
            graph[b].add(Node(a, c))
            left = left.coerceAtMost(c)
            right = right.coerceAtLeast(c)
        }

        while (left <= right) {
            val mid = (left + right) / 2

            if (bfs(n, mid)) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun bfs(n: Int, limit: Int): Boolean {
        val queue = LinkedList<Int>()
        val visited = BooleanArray(n + 1)

        with(position.first) {
            queue.offer(this)
            visited[this] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur == position.second) return true

            for (next in graph[cur]) {
                if (visited[next.index] || limit > next.limit) continue

                visited[next.index] = true
                queue.offer(next.index)
            }
        }
        return false
    }

    private data class Node(val index: Int, val limit: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val readIntArray = {
            br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        val (n, m) = readIntArray()
        val edges = Array(m) {
            readIntArray().let { Triple(it[0], it[1], it[2]) }
        }
        val dest = readIntArray().let { it[0] to it[1] }

        Problem1939().solution(n, edges, dest).also { bw.write("$it") }
        bw.flush()
    }
}

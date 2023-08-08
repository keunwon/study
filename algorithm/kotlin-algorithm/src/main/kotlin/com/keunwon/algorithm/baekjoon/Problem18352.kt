package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 특정 거리의 도시 찾기
 * Level: 실버-2
 **/
class Problem18352 {
    fun solution(n: Int, k: Int, x: Int, edges: Array<Pair<Int, Int>>): IntArray {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            edges.forEach { (a, b) -> this[a].add(b) }
        }
        val queue = LinkedList<Node>().apply { offer(Node(x, 0)) }
        val visited = BooleanArray(n + 1).apply { this[x] = true }
        val answer = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.distance == k) {
                answer.add(cur.index)
                continue
            }

            for (next in graph[cur.index]) {
                if (visited[next]) continue

                visited[next] = true
                queue.offer(Node(next, cur.distance + 1))
            }
        }
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray()
    }

    private data class Node(val index: Int, val distance: Int)
}

fun main() {
    val (n, m, k, x) = readLine()!!.split(" ").map { it.toInt() }
    val edges = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }

    Problem18352().solution(n, k, x, edges).forEach(::println)
}

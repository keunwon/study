package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1260 {
    private lateinit var graph: Array<MutableList<Int>>

    private val answer = Array(2) { mutableListOf<Int>() }

    fun solution(n: Int, v: Int, edges: Array<Pair<Int, Int>>): Array<IntArray> {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            edges.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        graph.forEach { it.sort() }

        dfs(v, BooleanArray(n + 1))
        bfs(v, BooleanArray(n + 1))
        return answer.map { it.toIntArray() }.toTypedArray()
    }

    private fun dfs(cur: Int, visited: BooleanArray) {
        if (visited[cur]) return

        visited[cur] = true
        answer[0].add(cur)

        for (next in graph[cur]) {
            if (!visited[next]) dfs(next, visited)
        }
    }

    private fun bfs(v: Int, visited: BooleanArray) {
        val queue = LinkedList<Int>().apply { offer(v) }
        visited[v] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            answer[1].add(cur)

            for (next in graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true
                    queue.offer(next)
                }
            }
        }
    }
}

fun main() {
    val (n, m, v) = readln().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val st = StringTokenizer(readln())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }

    Problem1260().solution(n, v, edges).forEach { println(it.joinToString(" ")) }
}

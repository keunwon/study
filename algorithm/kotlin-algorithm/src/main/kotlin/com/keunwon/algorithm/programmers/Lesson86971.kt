package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.abs
import kotlin.math.min

class Lesson86971 {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        val graph = Array(n + 1) { IntArray(n + 1) }.apply {
            wires.forEach { (a, b) ->
                this[a][b] = 1
                this[b][a] = 1
            }
        }

        var answer = n
        wires.forEach { (a, b) ->
            graph[a][b] = 0
            graph[b][a] = 0

            answer = min(answer, bfs(n, graph))

            graph[a][b] = 1
            graph[b][a] = 1
        }
        return answer
    }

    private fun bfs(n: Int, graph: Array<IntArray>): Int {
        val queue = LinkedList<Int>().apply { offer(1) }
        val visited = BooleanArray(n + 1).apply { this[1] = true }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (i in 1..n) {
                if (graph[cur][i] == 1 && !visited[i]) {
                    visited[i] = true
                    queue.offer(i)
                }
            }
        }

        val count = visited.count { it }
        return abs(abs(n - count) - count)
    }
}

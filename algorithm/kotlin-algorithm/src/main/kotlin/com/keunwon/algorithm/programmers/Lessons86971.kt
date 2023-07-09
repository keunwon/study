package com.keunwon.algorithm.programmers

import java.util.*
import kotlin.math.abs

/**
 * Title: 전력망을 둘로 나누기
 * Level: 2
 **/
class Lessons86971 {
    private lateinit var graph: Array<IntArray>

    fun solution(n: Int, wires: Array<IntArray>): Int {
        graph = Array(n + 1) { IntArray(n + 1) }.apply {
            wires.forEach { (l, r) ->
                this[l][r] = 1
                this[r][l] = 1
            }
        }
        var answer = n

        for ((l, r) in wires) {
            graph[l][r] = 0
            graph[r][l] = 0

            answer = answer.coerceAtMost(bfs(n, l))

            graph[l][r] = 1
            graph[r][l] = 1
        }
        return answer
    }

    private fun bfs(n: Int, start: Int): Int {
        val queue = LinkedList<Int>().apply { offer(start) }
        val visited = BooleanArray(n + 1).apply { set(start, true) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (i in 1..n) {
                if (!visited[i] && graph[cur][i] == 1) {
                    visited[i] = true
                    queue.offer(i)
                }
            }
        }
        return visited.count { it }.let { abs(it - (n - it)) }
    }
}

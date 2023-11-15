package com.keunwon.algorithm.againresolve

import java.util.*
import kotlin.math.abs

class ALessons86971 {
    fun solution(n: Int, writes: Array<IntArray>): Int {
        val graph = Array(n + 1) { BooleanArray(n + 1) }.apply {
            writes.forEach { (v1, v2) ->
                this[v1][v2] = true
                this[v2][v1] = true
            }
        }
        var answer = n

        for ((v1, v2) in writes) {
            graph[v1][v2] = false
            graph[v2][v1] = false

            val count = bfs(n, v1, graph)
            answer = answer.coerceAtMost(count)

            graph[v1][v2] = true
            graph[v2][v1] = true
        }
        return answer
    }

    private fun bfs(n: Int, start: Int, graph: Array<BooleanArray>): Int {
        val queue = LinkedList<Int>().apply { offer(start) }
        val visited = BooleanArray(n + 1).apply { this[start] = true }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (i in 1..n) {
                if (!visited[i] && graph[cur][i]) {
                    visited[i] = true
                    queue.offer(i)
                }
            }
        }
        return abs(n - visited.count { it } * 2)
    }
}

fun main() {
    ALessons86971().solution(
        9,
        arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(4, 6),
            intArrayOf(4, 7),
            intArrayOf(7, 8),
            intArrayOf(7, 9)
        )
    ).also(::println)

    ALessons86971().solution(
        4,
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4)
        )
    ).also(::println)

    ALessons86971().solution(
        7,
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 7),
            intArrayOf(3, 7),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(6, 7)
        )
    ).also(::println)
}

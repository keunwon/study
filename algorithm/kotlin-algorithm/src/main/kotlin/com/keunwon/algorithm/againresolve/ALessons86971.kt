package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class ALessons86971 {
    private lateinit var graph: Array<BooleanArray>

    fun solution(n: Int, wires: Array<IntArray>): Int {
        this.graph = Array(n + 1) { BooleanArray(n + 1) }.apply {
            wires.forEach { (a, b) ->
                this[a][b] = true
                this[b][a] = true
            }
        }
        var answer = n

        wires.forEach { (a, b) ->
            graph[a][b] = false
            graph[b][a] = false

            val visited = BooleanArray(n + 1).apply { dfs(1, this) }
            val num1 = visited.count { it }
            val num2 = visited.count { !it } - 1
            answer = answer.coerceAtMost(abs(num1 - num2))

            graph[a][b] = true
            graph[b][a] = true
        }
        return answer
    }

    private fun dfs(cur: Int, visited: BooleanArray): Int {
        visited[cur] = true

        var answer = 1
        for (i in graph[cur].indices) {
            if (!visited[i] && graph[cur][i]) {
                answer += dfs(i, visited)
            }
        }
        return answer
    }
}

fun main() {
    val n = 9
    val wires = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(4, 6),
        intArrayOf(4, 7),
        intArrayOf(7, 8),
        intArrayOf(7, 9),
    )
    ALessons86971().solution(n, wires).also { println(it) }
}

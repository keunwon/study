package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons43162 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        if (n == 1) return 1

        val visited = BooleanArray(n)
        var answer = 0

        for (i in 0 until n) {
            if (!visited[i]) {
                visited[i] = true
                bfs(i, visited, computers)
                ++answer
            }
        }
        return answer
    }

    private fun bfs(start: Int, visited: BooleanArray, computers: Array<IntArray>) {
        val queue = LinkedList<Int>().apply { offer(start) }
        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in visited.indices) {
                if (!visited[i] && computers[cur][i] == 1) {
                    visited[i] = true
                    queue.offer(i)
                }
            }
        }
    }
}

fun main() {
    ALessons43162().solution(
        3,
        arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1))
    ).also(::println)

    ALessons43162().solution(
        3,
        arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1))
    ).also(::println)
}

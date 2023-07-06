package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 네트워크
 * Level: 3
 **/
class Lessons43162 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        if (n == 1) return 1

        val visited = BooleanArray(n)
        var answer = 0

        for (i in 0 until n) {
            if (!visited[i]) {
                visited[i] = true
                bfs(i, visited, computers)
                answer++
            }
        }
        return answer
    }

    private fun bfs(start: Int, visited: BooleanArray, computers: Array<IntArray>) {
        val queue = LinkedList<Int>().apply { offer(start) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            for (i in visited.indices) {
                if (visited[i] || computers[cur][i] == 0) continue
                queue.offer(i)
                visited[i] = true
            }
        }
    }
}

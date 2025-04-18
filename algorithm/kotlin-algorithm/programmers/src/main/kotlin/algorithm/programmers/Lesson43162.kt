package algorithm.programmers

import java.util.*

class Lesson43162 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val visited = BooleanArray(n)
        var answer = 0

        for (i in 0 until n) {
            if (!visited[i]) {
                bfs(i, visited, computers)
                ++answer
            }
        }
        return answer
    }

    private fun bfs(start: Int, visited: BooleanArray, computers: Array<IntArray>) {
        val queue = LinkedList<Int>().apply { offer(start) }
        visited[start] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((i, type) in computers[cur].withIndex()) {
                if (type == 1 && !visited[i]) {
                    visited[i] = true
                    queue.offer(i)
                }
            }
        }
    }
}

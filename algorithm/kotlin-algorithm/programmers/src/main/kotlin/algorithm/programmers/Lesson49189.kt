package algorithm.programmers

import java.util.*

class Lesson49189 {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            edge.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        val queue = LinkedList<Int>().apply { offer(1) }
        val dist = IntArray(n + 1) { 1e9.toInt() }.apply {
            this[0] = 0
            this[1] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                if (dist[next] > dist[cur] + 1) {
                    dist[next] = dist[cur] + 1
                    queue.offer(next)
                }
            }
        }

        val max = dist.maxOrNull() ?: 0
        return dist.count { it == max }
    }
}

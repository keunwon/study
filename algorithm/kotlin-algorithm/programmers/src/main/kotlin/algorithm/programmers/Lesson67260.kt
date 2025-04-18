package algorithm.programmers

import java.util.*

class Lesson67260 {
    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val graph = Array(n) { mutableListOf<Int>() }.apply {
            path.forEach { (u, v) ->
                this[u].add(v)
                this[v].add(u)
            }
        }
        val startMap = mutableMapOf<Int, Int>()
        val endMap = mutableMapOf<Int, Int>()
        val queue = LinkedList<Int>().apply { offer(0) }
        val status = IntArray(n) { NOT_VISITED }

        order.forEach { (a, b) ->
            startMap[a] = b
            endMap[b] = a
        }

        if (endMap.keys.contains(0)) return false

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for (next in graph[cur]) {
                if (status[next] != NOT_VISITED) continue

                if (endMap.containsKey(next)) {
                    if (status[endMap.getValue(next)] == VISITED) {
                        queue.offer(next)
                        status[next] = VISITED
                    } else {
                        status[next] = WAITED
                    }
                } else {
                    startMap[next]?.let { e ->
                        if (status[e] == WAITED) {
                            status[e] = VISITED
                            queue.offer(e)
                        }
                    }
                    queue.offer(next)
                    status[next] = VISITED
                }
            }
        }
        return status.all { it == VISITED }
    }

    companion object {
        private const val NOT_VISITED = 0
        private const val VISITED = 1
        private const val WAITED = 2
    }
}

package algorithm.programmers

import java.util.*

class Lesson12978 {
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val graph = Array(N + 1) { mutableListOf<Node>() }.apply {
            road.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        val dist = IntArray(N + 1) { 1e9.toInt() }.apply { this[1] = 0 }
        val queue = PriorityQueue<Node>(compareBy { it.d }).apply { offer(Node(1, 0)) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dist[cur.index] < cur.d) continue

            for (next in graph[cur.index]) {
                val d = cur.d + next.d
                if (dist[next.index] > d) {
                    dist[next.index] = d
                    queue.offer(Node(next.index, d))
                }
            }
        }
        return dist.count { it <= k }
    }

    private class Node(val index: Int, val d: Int)
}

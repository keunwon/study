package algorithm.programmers

import java.util.*
import kotlin.math.max

class Lesson118669 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            paths.forEach { (a, b, c) ->
                this[a].add(Node(b, c))
                this[b].add(Node(a, c))
            }
        }
        val queue = PriorityQueue<Node>(compareBy { it.d })
        val dist = IntArray(n + 1) { 1e9.toInt() }

        gates.forEach { gate ->
            queue.offer(Node(gate, 0))
            dist[gate] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (summits.contains(cur.index)) continue
            if (dist[cur.index] < cur.d) continue

            for (next in graph[cur.index]) {
                val d = max(cur.d, next.d)
                if (dist[next.index] > d) {
                    dist[next.index] = d
                    queue.offer(Node(next.index, d))
                }
            }
        }
        return summits.map { intArrayOf(it, dist[it]) }
            .sortedWith(compareBy({ it[1] }, { it[0] }))
            .first()
    }

    private data class Node(val index: Int, val d: Int)
}

package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons118669 {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val graph = Array(n + 1) { mutableListOf<Node>() }.apply {
            paths.forEach { (s, e, d) ->
                this[s].add(Node(e, d))
                this[e].add(Node(s, d))
            }
        }
        val queue = LinkedList<Node>()
        val dist = IntArray(n + 1) { 2e9.toInt() }

        gates.forEach {
            queue.offer(Node(it, 0))
            dist[it] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dist[cur.index] < cur.distance) continue
            if (summits.contains(cur.index)) continue

            for (next in graph[cur.index]) {
                val max = next.distance.coerceAtLeast(cur.distance)
                if (dist[next.index] > max) {
                    dist[next.index] = max
                    queue.offer(Node(next.index, max))
                }
            }
        }
        return summits.map { it to dist[it] }
            .sortedWith(compareBy({ it.second }, { it.first }))
            .first()
            .let { (a, b) -> intArrayOf(a, b) }
    }

    private class Node(val index: Int, val distance: Int)
}

fun main() {
    ALessons118669().solution(
        6,
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(2, 3, 5),
            intArrayOf(2, 4, 2),
            intArrayOf(2, 5, 4),
            intArrayOf(3, 4, 4),
            intArrayOf(4, 5, 3),
            intArrayOf(4, 6, 1),
            intArrayOf(5, 6, 1)
        ),
        intArrayOf(1, 3),
        intArrayOf(5)
    ).also { println(it.contentToString()) }

    ALessons118669().solution(
        7,
        arrayOf(
            intArrayOf(1, 4, 4),
            intArrayOf(1, 6, 1),
            intArrayOf(1, 7, 3),
            intArrayOf(2, 5, 2),
            intArrayOf(3, 7, 4),
            intArrayOf(5, 6, 6)
        ),
        intArrayOf(1),
        intArrayOf(2, 3, 4)
    ).also { println(it.contentToString()) }

    ALessons118669().solution(
        7,
        arrayOf(
            intArrayOf(1, 2, 5),
            intArrayOf(1, 4, 1),
            intArrayOf(2, 3, 1),
            intArrayOf(2, 6, 7),
            intArrayOf(4, 5, 1),
            intArrayOf(5, 6, 1),
            intArrayOf(6, 7, 1)
        ),
        intArrayOf(3, 7),
        intArrayOf(1, 5)
    ).also { println(it.contentToString()) }

    ALessons118669().solution(
        5,
        arrayOf(
            intArrayOf(1, 3, 10),
            intArrayOf(1, 4, 20),
            intArrayOf(2, 3, 4),
            intArrayOf(2, 4, 6),
            intArrayOf(3, 5, 20),
            intArrayOf(4, 5, 6)
        ),
        intArrayOf(1, 2),
        intArrayOf(5)
    ).also { println(it.contentToString()) }
}

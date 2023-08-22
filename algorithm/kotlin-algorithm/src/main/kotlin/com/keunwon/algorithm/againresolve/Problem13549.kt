package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 숨바꼭질 3
 * Level: 골드-5
 **/
class Problem13549 {
    fun solution(n: Int, k: Int): Int {
        val queue = LinkedList<Node>()
        val dist = IntArray(INF) { INF }

        queue.offer(Node(n, 0))
        dist[n] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dist[cur.index] < cur.time) continue


            val options = arrayOf(
                Pair(cur.index * 2, cur.time),
                Pair(cur.index + 1, cur.time + 1),
                Pair(cur.index - 1, cur.time + 1),
            )

            for ((index, time) in options) {
                if (index !in 0 until INF) continue
                if (dist[index] <= time) continue

                dist[index] = time
                queue.offer(Node(index, time))
            }
        }
        return dist[k]
    }

    private data class Node(val index: Int, val time: Int)

    companion object {
        private const val INF = 100_001
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    Problem13549().solution(n, k).also(::println)
}

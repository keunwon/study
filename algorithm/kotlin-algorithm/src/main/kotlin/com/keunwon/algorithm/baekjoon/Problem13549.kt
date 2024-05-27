package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem13549 {
    fun solution(n: Int, k: Int): Int {
        val inf = 100_000
        val queue = PriorityQueue<Node>(compareBy { it.time }).apply { offer(Node(n, 0)) }
        val times = IntArray(inf + 1) { inf + 1 }

        if (n == k) return 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (times[cur.index] < cur.time) continue
            if (cur.index == k) continue

            val nextIndexes = intArrayOf(cur.index - 1, cur.index + 1, cur.index * 2)
            val nextTimes = intArrayOf(cur.time + 1, cur.time + 1, cur.time)

            for (i in 0 until 3) {
                val index = nextIndexes[i]
                val time = nextTimes[i]

                if (index in times.indices && times[index] > time) {
                    times[index] = time
                    queue.offer(Node(index, time))
                }
            }
        }
        return times[k]
    }

    private data class Node(val index: Int, val time: Int)
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    println(Problem13549().solution(n, k))
}

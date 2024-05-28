package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem4485 {
    fun solution(map: Array<IntArray>): Int {
        val queue = PriorityQueue<Node>(compareBy { it.cost }).apply {
            offer(Node(0, 0, map[0][0]))
        }
        val dist = Array(map.size) { IntArray(map[0].size) { 1e9.toInt() } }.apply {
            this[0][0] = map[0][0]
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dist[cur.r][cur.c] < cur.cost) continue
            if (cur.r == map.lastIndex && cur.c == map[0].lastIndex) continue

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue

                val cost = cur.cost + map[nr][nc]
                if (dist[nr][nc] > cost) {
                    dist[nr][nc] = cost
                    queue.offer(Node(nr, nc, cost))
                }
            }
        }
        return dist[map.lastIndex][map[0].lastIndex]
    }

    private data class Node(val r: Int, val c: Int, val cost: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    var index = 1

    while (true) {
        val n = readln().toInt()
        if (n == 0) break
        val map = Array(n) {
            val st = StringTokenizer(readln())
            IntArray(n) { st.nextToken().toInt() }
        }

        println("Problem ${index++}: ${Problem4485().solution(map)}")
    }
}

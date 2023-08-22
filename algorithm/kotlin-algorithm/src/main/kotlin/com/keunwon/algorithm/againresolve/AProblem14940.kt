package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem14940 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Array<IntArray> {
        val queue = PriorityQueue<Node>(compareBy { it.distance })
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        val dist = Array(map.size) { IntArray(map[0].size) { -1 } }

        with(findStartPosition(map)) {
            queue.offer(Node(first, second, 0))
            visited[first][second] = true
            dist[first][second] = 0
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] == 0 || visited[nr][nc]) continue

                queue.offer(Node(nr, nc, cur.distance + 1))
                visited[nr][nc] = true
                dist[nr][nc] = cur.distance + 1
            }
        }

        for (i in dist.indices) {
            for (j in dist[i].indices) {
                if (map[i][j] == 0) dist[i][j] = 0
            }
        }
        return dist
    }

    private fun findStartPosition(map: Array<IntArray>): Pair<Int, Int> {
        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] == 2) return i to j
            }
        }
        error("")
    }

    private data class Node(val r: Int, val c: Int, val distance: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(m) { st.nextToken().toInt() }
        }

        AProblem14940().solution(map).forEach {
            bw.write("${it.joinToString(" ")}")
            bw.newLine()
        }
        bw.flush()
    }
}

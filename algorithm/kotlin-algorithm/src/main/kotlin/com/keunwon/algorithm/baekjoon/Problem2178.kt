package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 미로 탐색
 * Level: 실버-1
 **/
class Problem2178 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }

        queue.offer(Node(0, 0, 1))
        visited[0][0] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.finish(map)) return cur.distance

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] == 0 || visited[nr][nc]) continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc, cur.distance + 1))
            }
        }
        return 0
    }

    private data class Node(
        val r: Int,
        val c: Int,
        val distance: Int,
    ) {
        fun finish(map: Array<IntArray>): Boolean {
            return r == map.lastIndex && c == map[0].lastIndex
        }
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            br.readLine().toCharArray().map { it.digitToInt() }.toIntArray()
        }

        Problem2178().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}

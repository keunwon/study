package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 벽 부수고 이동하기
 * Level: 골드-3
 **/
class Problem2206 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { Array(map[0].size) { BooleanArray(2) } }

        queue.offer(Node(0, 0, 0, 1))
        visited[0][0][0] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == map.lastIndex && cur.c == map[0].lastIndex) return cur.distance

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue

                if (map[nr][nc] == 1 && cur.skill == 0 && !visited[nr][nc][1]) {
                    queue.offer(Node(nr, nc, 1, cur.distance + 1))
                    visited[nr][nc][1] = true
                } else if (map[nr][nc] == 0 && !visited[nr][nc][cur.skill]) {
                    queue.offer(Node(nr, nc, cur.skill, cur.distance + 1))
                    visited[nr][nc][cur.skill] = true
                }
            }
        }
        return -1
    }

    private data class Node(
        val r: Int,
        val c: Int,
        val skill: Int,
        val distance: Int,
    )
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            br.readLine().map { it.digitToInt() }.toIntArray()
        }

        Problem2206().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}

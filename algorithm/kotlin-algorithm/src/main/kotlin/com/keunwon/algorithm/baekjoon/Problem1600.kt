package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 말이 되고픈 원숭이
 * Level: 골드-3
 **/
class Problem1600 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private val diagonals = arrayOf(-2 to -1, -2 to 1, -1 to -2, -1 to 2, 1 to -2, 1 to 2, 2 to -1, 2 to 1)
    private lateinit var map: Array<IntArray>

    fun solution(k: Int, map: Array<IntArray>): Int {
        this.map = map
        return bfs(k)
    }

    private fun bfs(k: Int): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { Array(map[0].size) { BooleanArray(k + 1) } }

        queue.offer(Node(0, 0, 0, k))
        visited[0][0][k] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == map.lastIndex && cur.c == map[0].lastIndex) return cur.distance

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc][cur.skill] || map[nr][nc] == 1) continue

                queue.offer(Node(nr, nc, cur.distance + 1, cur.skill))
                visited[nr][nc][cur.skill] = true
            }

            if (cur.skill == 0) continue

            for ((mr, mc) in diagonals) {
                val nr = cur.r + mr
                val nc = cur.c + mc
                val skill = cur.skill - 1

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc][skill] || map[nr][nc] == 1) continue

                queue.offer(Node(nr, nc, cur.distance + 1, skill))
                visited[nr][nc][skill] = true
            }
        }
        return -1
    }

    private data class Node(val r: Int, val c: Int, val distance: Int, val skill: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val k = br.readLine().toInt()
        val (w, h) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(h) {
            val st = StringTokenizer(br.readLine())
            IntArray(w) { st.nextToken().toInt() }
        }

        Problem1600().solution(k, map).also { bw.write("$it") }
        bw.flush()
    }
}

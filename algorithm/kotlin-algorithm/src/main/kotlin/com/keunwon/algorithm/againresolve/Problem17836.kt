package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 공주님을 구해라!
 * Level: 골드-5
 **/
class Problem17836 {
    private lateinit var map: Array<IntArray>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(t: Int, map: Array<IntArray>): String {
        this.map = map
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { Array(map[0].size) { BooleanArray(2) } }

        queue.offer(Node(0, 0, 0, false))
        visited[0][0][0] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.distance > t) break
            if (cur.r == map.lastIndex && cur.c == map[0].lastIndex) return "${cur.distance}"

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue

                if (cur.skill && !visited[nr][nc][1]) {
                    queue.offer(Node(nr, nc, cur.distance + 1, cur.skill))
                    visited[nr][nc][1] = true
                } else if (!cur.skill) {
                    if (!visited[nr][nc][0] && map[nr][nc] == 0) {
                        queue.offer(Node(nr, nc, cur.distance + 1, cur.skill))
                        visited[nr][nc][0] = true
                    } else if (!visited[nr][nc][0] && map[nr][nc] == 2) {
                        queue.offer(Node(nr, nc, cur.distance + 1, true))
                        visited[nr][nc][0] = true
                    }
                }
            }
        }
        return "Fail"
    }

    private data class Node(
        val r: Int,
        val c: Int,
        val distance: Int,
        val skill: Boolean,
    )
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m, t) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(m) { st.nextToken().toInt() }
        }

        Problem17836().solution(t, map).also { bw.write("$it") }
        bw.flush()
    }
}

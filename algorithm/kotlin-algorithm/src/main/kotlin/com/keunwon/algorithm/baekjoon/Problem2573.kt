package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 빙산
 * Level: 골드-4
 **/
class Problem2573 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private lateinit var map: Array<IntArray>

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        var answer = 0

        while (true) {
            val area = countByArea()
            if (area > 1) break
            if (area == 0) return 0

            melt()
            answer++
        }
        return answer
    }

    private fun melt() {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }

        for (i in map.indices) {
            for ((j, num) in map[i].withIndex()) {
                if (num == 0) continue

                queue.offer(Node(i, j))
                visited[i][j] = true
            }
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            var sea = 0

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (!isRange(nr, nc)) continue
                if (visited[nr][nc] || map[nr][nc] != 0) continue

                sea++
            }

            val tmp = map[cur.r][cur.c]
            map[cur.r][cur.c] = (tmp - sea).coerceAtLeast(0)
        }
    }

    private fun countByArea(): Int {
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        var count = 0

        for (i in map.indices) {
            for ((j, num) in map[i].withIndex()) {
                if (num == 0 || visited[i][j]) continue

                dfs(Node(i, j), visited)
                count++
            }
        }
        return count
    }

    private fun dfs(node: Node, visited: Array<BooleanArray>) {
        visited[node.r][node.c] = true

        for ((mr, mc) in moves) {
            val nr = node.r + mr
            val nc = node.c + mc

            if (!isRange(nr, nc)) continue
            if (visited[nr][nc] || map[nr][nc] == 0) continue

            dfs(Node(nr, nc), visited)
        }
    }

    private fun isRange(r: Int, c: Int): Boolean {
        return r in map.indices && c in map[0].indices
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (h, w) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(h) {
            val st = StringTokenizer(br.readLine())
            IntArray(w) { st.nextToken().toInt() }
        }

        Problem2573().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}

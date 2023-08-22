package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 연구소
 * Level: 골드-4
 **/
class Problem14502 {
    private lateinit var map: Array<IntArray>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var answer = 0

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        dfs(0)
        return answer
    }

    private fun dfs(depth: Int) {
        if (depth == 3) {
            answer = answer.coerceAtLeast(bfs())
            return
        }

        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] != 0) continue

                map[i][j] = 1
                dfs(depth + 1)
                map[i][j] = 0
            }
        }
    }

    private fun bfs(): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }

        for (i in map.indices) {
            for ((j, num) in map[i].withIndex()) {
                if (num == 1) visited[i][j] = true
                if (num == 2) {
                    queue.offer(Node(i, j))
                    visited[i][j] = true
                }
            }
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] != 0) continue

                queue.offer(Node(nr, nc))
                visited[nr][nc] = true
            }
        }
        return visited.sumOf { arr -> arr.count { !it } }
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(m) { st.nextToken().toInt() }
        }

        Problem14502().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}

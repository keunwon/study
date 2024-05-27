package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.abs

class Problem16234 {
    private lateinit var map: Array<IntArray>
    private lateinit var diffRange: IntRange

    fun solution(map: Array<IntArray>, l: Int, r: Int): Int {
        this.map = map
        this.diffRange = l..r
        var answer = 0

        while (true) {
            val visited = Array(map.size) { BooleanArray(map[0].size) }
            var flag = false

            for (i in map.indices) {
                for (j in map[0].indices) {
                    if (!visited[i][j] && bfs(visited, Node(i, j))) {
                        flag = true
                    }
                }
            }

            if (!flag) break
            ++answer
        }
        return answer
    }

    private fun bfs(visited: Array<BooleanArray>, start: Node): Boolean {
        val queue = LinkedList<Node>().apply { offer(start) }
        val set = mutableSetOf<Node>().apply { add(start) }
        visited[start.r][start.c] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            set.add(cur)

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in visited.indices || nc !in visited[0].indices) continue

                val diff = abs(map[nr][nc] - map[cur.r][cur.c])
                if (!visited[nr][nc] && diff in diffRange) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc))
                }
            }
        }
        if (set.size == 1) return false

        val avg = set.sumOf { map[it.r][it.c] } / set.size
        set.forEach { (r, c) -> map[r][c] = avg }
        return true
    }

    private data class Node(val r: Int, val c: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem16234().solution(map, l, r))
}

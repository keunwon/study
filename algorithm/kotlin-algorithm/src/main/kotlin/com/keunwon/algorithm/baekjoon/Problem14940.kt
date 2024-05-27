package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem14940 {
    fun solution(map: Array<IntArray>): Array<IntArray> {
        val queue = LinkedList<Node>()
        val dp = Array(map.size) { IntArray(map[0].size) { -1 } }
        val visited = Array(map.size) { BooleanArray(map[0].size) }

        var flag = false
        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                if (type == 2) {
                    queue.offer(Node(i, j, 0))
                    visited[i][j] = true
                    flag = true
                    break
                }
            }
            if (flag) break
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            dp[cur.r][cur.c] = cur.d

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] == 0) continue

                queue.offer(Node(nr, nc, cur.d + 1))
                visited[nr][nc] = true
            }
        }

        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                if (type == 0) dp[i][j] = 0
            }
        }
        return dp
    }

    private class Node(val r: Int, val c: Int, val d: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    Problem14940().solution(map).forEach { println(it.joinToString(" ")) }
}

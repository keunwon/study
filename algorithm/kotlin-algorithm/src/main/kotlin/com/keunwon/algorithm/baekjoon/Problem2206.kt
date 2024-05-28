package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem2206 {
    fun solution(map: Array<IntArray>): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { Array(map[0].size) { BooleanArray(2) } }

        queue.offer(Node(0, 0, 1))
        visited[0][0][map[0][0]] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == map.lastIndex && cur.c == map[0].lastIndex) return cur.d

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue

                if (map[nr][nc] == 1 && !visited[nr][nc][1] && !cur.broken) {
                    visited[nr][nc][1] = true
                    queue.offer(Node(nr, nc, cur.d + 1, true))
                } else if (map[nr][nc] == 0 && !visited[nr][nc][cur.brokenType]) {
                    visited[nr][nc][cur.brokenType] = true
                    queue.offer(Node(nr, nc, cur.d + 1, cur.broken))
                }
            }
        }
        return -1
    }

    private class Node(
        val r: Int,
        val c: Int,
        val d: Int,
        var broken: Boolean = false,
    ) {
        val brokenType: Int
            get() = if (broken) 1 else 0
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().map { it.digitToInt() }.toIntArray() }

    println(Problem2206().solution(map))
}

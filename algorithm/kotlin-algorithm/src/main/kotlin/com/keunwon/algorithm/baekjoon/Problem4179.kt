package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem4179 {
    private lateinit var map: Array<CharArray>

    fun solution(map: Array<CharArray>): String {
        this.map = map
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        var start: Node? = null

        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                if (type == 'F') {
                    queue.offer(Node(i, j, type, 0))
                } else if (type == 'J') {
                    if (finish(i, j)) return "1"

                    map[i][j] = '.'
                    visited[i][j] = true
                    start = Node(i, j, type, 0)
                }
            }
        }
        if (start != null) queue.offer(start)

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.type == 'J' && finish(cur.r, cur.c)) return "${cur.distance + 1}"

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] in charArrayOf('F', '#')) continue

                if (cur.type == 'F') {
                    map[nr][nc] = 'F'
                    queue.offer(Node(nr, nc, cur.type, cur.distance + 1))
                } else if (cur.type == 'J' && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.type, cur.distance + 1))
                }
            }
        }
        return "IMPOSSIBLE"
    }

    private fun finish(r: Int, c: Int): Boolean {
        for ((mr, mc) in moves) {
            val nr = r + mr
            val nc = c + mc

            if (nr !in map.indices || nc !in map[0].indices) return true
        }
        return false
    }

    private data class Node(
        val r: Int,
        val c: Int,
        val type: Char,
        val distance: Int,
    )

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val map = Array(r) { readln().toCharArray() }

    println(Problem4179().solution(map))
}

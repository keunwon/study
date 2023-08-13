package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem4179 {
    private lateinit var map: Array<CharArray>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<CharArray>): String {
        this.map = map
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        var start: Node? = null

        for (i in map.indices) {
            for (j in map[0].indices) {
                when (map[i][j]) {
                    'J' -> {
                        if (finish(i, j)) return "1"
                        map[i][j] = '.'
                        start = Node(i, j, 'J', 0)
                    }
                    'F' -> queue.offer(Node(i, j, 'F', 0))
                }
            }
        }

        return start?.let { node ->
            queue.offer(node)
            visited[node.r][node.c] = true
            bfs(queue, visited)
        } ?: ""
    }

    private fun bfs(queue: LinkedList<Node>, visited: Array<BooleanArray>): String {
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.type == 'J' && finish(cur.r, cur.c)) return "${cur.second + 1}"

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] != '.') continue

                if (cur.type == 'F') {
                    map[nr][nc] = 'F'
                    queue.offer(Node(nr, nc, cur.type, cur.second + 1))
                } else if (cur.type == 'J' && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.offer(Node(nr, nc, cur.type, cur.second + 1))
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

    private data class Node(val r: Int, val c: Int, val type: Char, val second: Int)
}

fun main() {
    val (r, c) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(r) { readLine()!!.toCharArray() }

    AProblem4179().solution(map).also { println(it) }
}
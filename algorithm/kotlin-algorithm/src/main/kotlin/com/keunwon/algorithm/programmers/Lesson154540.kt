package com.keunwon.algorithm.programmers

import java.util.*

class Lesson154540 {
    private lateinit var map: Array<String>
    private lateinit var visited: Array<BooleanArray>

    fun solution(maps: Array<String>): IntArray {
        this.map = maps
        this.visited = Array(maps.size) { BooleanArray(maps[0].length) }
        val answer = mutableListOf<Int>()

        for (i in map.indices) {
            for (j in map[0].indices) {
                if (!visited[i][j] && map[i][j] != 'X') {
                    answer.add(bfs(Node(i, j)))
                }
            }
        }
        if (answer.isEmpty()) return intArrayOf(-1)
        return answer.sorted().toIntArray()
    }

    private fun bfs(start: Node): Int {
        val queue = LinkedList<Node>().apply { offer(start) }
        var food = 0
        visited[start.r][start.c] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            food += map[cur.r][cur.c] - '0'

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] == 'X') continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc))
            }
        }
        return food
    }

    private data class Node(val r: Int, val c: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

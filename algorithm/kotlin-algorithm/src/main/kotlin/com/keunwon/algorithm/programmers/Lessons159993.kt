package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 미로 탈출
 * Level: 2
 **/
class Lessons159993 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(maps: Array<String>): Int {
        val start = IntArray(2)
        val exit = IntArray(2)
        val lever = IntArray(2)

        for (i in maps.indices) {
            for (j in maps[0].indices) {
                when (maps[i][j]) {
                    'S' -> start
                    'E' -> exit
                    'L' -> lever
                    else -> null
                }?.let {
                    it[0] = i
                    it[1] = j
                }
            }
        }

        val second1 = bfs(start[0], start[1], lever[0], lever[1], maps).also { if (it == -1) return -1 }
        val second2 = bfs(lever[0], lever[1], exit[0], exit[1], maps).also { if (it == -1) return -1 }
        return second1 + second2
    }

    private fun bfs(sr: Int, sc: Int, er: Int, ec: Int, maps: Array<String>): Int {
        val queue = LinkedList<IntArray>().apply { offer(intArrayOf(sr, sc, 0)) }
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }

        while (queue.isNotEmpty()) {
            val (r, c, second) = queue.poll()

            if (r == er && c == ec) return second

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr !in maps.indices || nc !in maps[0].indices) continue
                if (visited[nr][nc] || maps[nr][nc] == 'X') continue

                queue.offer(intArrayOf(nr, nc, second + 1))
                visited[nr][nc] = true
            }
        }
        return -1
    }
}

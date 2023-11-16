package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons159993 {
    private lateinit var map: Array<String>

    fun solution(maps: Array<String>): Int {
        this.map = maps
        lateinit var start: Pair<Int, Int>
        lateinit var end: Pair<Int, Int>
        lateinit var lever: Pair<Int, Int>

        for (i in map.indices) {
            for ((j, type) in map[i].withIndex()) {
                when (type) {
                    'S' -> start = i to j
                    'E' -> end = i to j
                    'L' -> lever = i to j
                }
            }
        }

        val distance1 = bfs(start, lever)
        val distance2 = bfs(lever, end)
        return if (distance1 != -1 && distance2 != -1) distance1 + distance2 else -1
    }

    private fun bfs(start: Pair<Int, Int>, dest: Pair<Int, Int>): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].length) }

        start.let { (r, c) ->
            queue.offer(Node(r, c, 0))
            visited[r][c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dest == Pair(cur.r, cur.c)) return cur.distance

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in visited.indices || nc !in visited[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] == 'X') continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc, cur.distance + 1))
            }
        }
        return -1
    }

    private data class Node(val r: Int, val c: Int, val distance: Int = 0)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    ALessons159993().solution(
        arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")
    ).also(::println)

    ALessons159993().solution(
        arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")
    ).also(::println)
}

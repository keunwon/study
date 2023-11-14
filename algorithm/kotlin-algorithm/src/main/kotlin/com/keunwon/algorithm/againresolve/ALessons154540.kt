package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons154540 {
    fun solution(maps: Array<String>): IntArray {
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }
        val answer = mutableListOf<Int>()

        for (i in maps.indices) {
            for ((j, c) in maps[i].withIndex()) {
                if (!visited[i][j] && c != 'X') {
                    val point = bfs(Pair(i, j), visited, maps)
                    answer.add(point)
                }
            }
        }
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray()
    }

    private fun bfs(start: Pair<Int, Int>, visited: Array<BooleanArray>, maps: Array<String>): Int {
        val queue = LinkedList<Pair<Int, Int>>()
        var total = 0

        start.let { (r, c) ->
            queue.offer(Pair(r, c))
            visited[r][c] = true
        }

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()
            total += maps[r][c] - '0'

            for ((mr, mc) in moves) {
                val (nr, nc) = Pair(r + mr, c + mc)

                if (nr !in visited.indices || nc !in visited[0].indices) continue
                if (visited[nr][nc] || maps[nr][nc] == 'X') continue

                visited[nr][nc] = true
                queue.offer(Pair(nr, nc))
            }
        }
        return total
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    ALessons154540().solution(
        arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
    ).also { println(it.contentToString()) }

    ALessons154540().solution(
        arrayOf("XXX", "XXX", "XXX")
    ).also { println(it.contentToString()) }
}

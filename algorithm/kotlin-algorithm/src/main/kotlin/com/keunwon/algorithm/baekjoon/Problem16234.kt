package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.abs

/**
 * Title: 인구 이동
 * Level: 골드-5
 **/
class Problem16234(
    private val l: Int,
    private val r: Int,
    private val map: Array<IntArray>,
) {
    fun solution(): Int {
        var answer = 0

        while (true) {
            val visited = Array(map.size) { BooleanArray(map[0].size) }
            var flag = false

            for (i in map.indices) {
                for (j in map[0].indices) {
                    if (!visited[i][j] && bfs(i, j, visited)) flag = true
                }
            }

            if (!flag) break
            answer++
        }
        return answer
    }

    private fun bfs(sr: Int, sc: Int, visited: Array<BooleanArray>): Boolean {
        val queue = LinkedList<Position>().apply { offer(Position(sr, sc)) }
        val set = mutableSetOf<Position>().apply { add(Position(sr, sc)) }

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || !isMerge(map[r][c], map[nr][nc])) continue

                queue.offer(Position(nr, nc))
                set.add(Position(nr, nc))
                visited[nr][nc] = true
            }
        }

        if (set.size == 1) return false

        val num = set.sumOf { map[it.r][it.c] } / set.size
        set.forEach { map[it.r][it.c] = num }
        return true
    }

    private fun isMerge(cur: Int, next: Int): Boolean {
        val diff = abs(cur - next)
        return diff in l..r
    }

    private data class Position(val r: Int, val c: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val (n, l, r) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem16234(l, r, arr).solution().also { println(it) }
}

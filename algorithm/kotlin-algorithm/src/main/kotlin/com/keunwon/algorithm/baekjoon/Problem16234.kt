package com.keunwon.algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.abs

/**
 * <p>
 * 이름: 인구이동
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/16234">인구이동 (골드-4)</a>
 **/
class Problem16234 {
    fun solution(l: Int, r: Int, map: Array<IntArray>): Int {
        var result = 0
        val range = l..r

        while (true) {
            val visited = Array(map.size) { BooleanArray(map[0].size) }
            var flag = false

            for (i in map.indices) {
                for (j in map[i].indices) {
                    if (!visited[i][j] && bfs(range, Pair(i, j), map, visited)) {
                        flag = true
                    }
                }
            }

            if (!flag) break
            ++result
        }
        return result
    }

    private fun bfs(
        range: IntRange,
        p: Pair<Int, Int>,
        map: Array<IntArray>,
        visited: Array<BooleanArray>,
    ): Boolean {
        val q = LinkedList<Pair<Int, Int>>().apply { offer(p) }
        val nodes = mutableListOf<Pair<Int, Int>>()

        visited[p.first][p.second] = true

        while (q.isNotEmpty()) {
            val (r, c) = q.poll()
            nodes.add(Pair(r, c))

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || abs(map[r][c] - map[nr][nc]) !in range) continue

                visited[nr][nc] = true
                q.offer(nr to nc)
            }
        }

        if (nodes.size == 1) return false

        val num = nodes.sumOf { (r, c) -> map[r][c] } / nodes.size
        nodes.forEach { (r, c) -> map[r][c] = num }
        return true
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, l, r) = readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        val arr = readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    println(Problem16234().solution(l, r, map))
}

package com.keunwon.algorithm.baekjoon

/**
 * Title: 진우의 달 여행 (Small)
 * Level: 실버-3
 **/
class Problem17484 {
    private val moves = intArrayOf(-1, 0, 1)
    private var answer = Int.MAX_VALUE

    fun solution(map: Array<IntArray>): Int {
        for (i in map[0].indices) {
            val visited = IntArray(map.size).apply { set(0, i) }
            dfs(1, -1, i, visited, map)
        }
        return answer
    }

    private fun dfs(depth: Int, dir: Int, x: Int, visited: IntArray, map: Array<IntArray>) {
        if (depth == map.size) {
            val sum = visited.mapIndexed { index, num -> map[index][num] }.sum()
            answer = answer.coerceAtMost(sum)
            return
        }

        for ((index, mx) in moves.withIndex()) {
            val nx = x + mx

            if (nx !in map[0].indices || dir == index) continue

            visited[depth] = nx
            dfs(depth + 1, index, nx, visited, map)
        }
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem17484().solution(map).also { println(it) }
}

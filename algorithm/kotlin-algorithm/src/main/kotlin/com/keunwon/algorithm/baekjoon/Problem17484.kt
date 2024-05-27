package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.min

class Problem17484 {
    private lateinit var map: Array<IntArray>
    private var answer = Int.MAX_VALUE

    fun solution(map: Array<IntArray>): Int {
        this.map = map

        for (i in map[0].indices) {
            val visited = IntArray(map.size).apply { this[0] = i }
            dfs(1, -1, i, visited)
        }
        return answer
    }

    private fun dfs(depth: Int, dir: Int, cur: Int, visited: IntArray) {
        if (depth == visited.size) {
            val sum = visited.mapIndexed { index, n -> map[index][n] }.sum()
            answer = min(answer, sum)
            return
        }

        for ((mDir, move) in moves.withIndex()) {
            val next = cur + move

            if (next in map[0].indices && dir != mDir) {
                visited[depth] = next
                dfs(depth + 1, mDir, next, visited)
            }
        }
    }

    companion object {
        private val moves = arrayOf(-1, 0, 1)
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    println(Problem17484().solution(map))
}

package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class ALessons12952 {
    private var answer = 0

    fun solution(n: Int): Int {
        dfs(0, IntArray(n), BooleanArray(n))
        return answer
    }

    private fun dfs(depth: Int, pick: IntArray, visited: BooleanArray) {
        if (depth == pick.size) {
            answer++
            return
        }
        for (i in pick.indices) {
            if (visited[i]) continue

            visited[i] = true
            pick[depth] = i
            if (check(depth, pick)) dfs(depth + 1, pick, visited)
            visited[i] = false
        }
    }

    private fun check(depth: Int, pick: IntArray): Boolean {
        for (i in 0 until depth) {
            if (pick[depth] == pick[i]) return false
            if (abs(pick[depth] - pick[i]) == depth - i) return false
        }
        return true
    }
}

fun main() {
    val n = 4
    ALessons12952().solution(n).also { println(it) }
}

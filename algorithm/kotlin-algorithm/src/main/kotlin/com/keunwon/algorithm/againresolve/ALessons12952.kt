package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class ALessons12952 {
    private var answer = 0

    fun solution(n: Int): Int {
        backtracking(0, IntArray(n), BooleanArray(n))
        return answer
    }

    private fun backtracking(depth: Int, picks: IntArray, visited: BooleanArray) {
        if (depth == picks.size) {
            ++answer
            return
        }

        for (i in visited.indices) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = i

            if (check(depth, picks)) backtracking(depth + 1, picks, visited)
            visited[i] = false
        }
    }

    private fun check(depth: Int, picks: IntArray): Boolean {
        for (i in 0 until depth) {
            if (abs(picks[depth] - picks[i]) == abs(depth - i)) return false
        }
        return true
    }
}


fun main() {
    ALessons12952().solution(4).also(::println)
}

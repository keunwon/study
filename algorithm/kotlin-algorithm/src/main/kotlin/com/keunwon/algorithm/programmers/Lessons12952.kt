package com.keunwon.algorithm.programmers

import kotlin.math.abs

/**
 * Title: N-Queen
 * Level: 2
 **/
class Lessons12952 {
    private var answer = 0

    fun solution(n: Int): Int {
        dfs(0, n, IntArray(n), BooleanArray(n))
        return answer
    }

    private fun dfs(depth: Int, n: Int, picks: IntArray, visited: BooleanArray) {
        if (depth == n) {
            answer++
            return
        }

        for (i in 0 until n) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = i

            if (check(depth, picks)) dfs(depth + 1, n, picks, visited)
            visited[i] = false
        }
    }

    private fun check(target: Int, picks: IntArray): Boolean {
        for (i in 0 until target) {
            if (abs(target - i) == abs(picks[target] - picks[i])) return false
        }
        return true
    }
}

package com.keunwon.algorithm.programmers

import kotlin.math.abs

class Lesson12952 {
    private var answer = 0

    fun solution(n: Int): Int {
        dfs(0, IntArray(n), BooleanArray(n), n)
        return answer
    }

    private fun dfs(depth: Int, picks: IntArray, visited: BooleanArray, n: Int) {
        if (depth == n) {
            ++answer
            return
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                visited[i] = true
                picks[depth] = i
                if (check(depth, picks, i)) dfs(depth + 1, picks, visited, n)
                visited[i] = false
            }
        }
    }

    private fun check(depth: Int, picks: IntArray, cur: Int): Boolean {
        for (i in 0 until depth) {
            if (depth - i == abs(picks[i] - cur)) return false
        }
        return true
    }
}
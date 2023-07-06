package com.keunwon.algorithm.programmers

/**
 * Title: 피로도
 * Level: 2
 **/
class Lessons87946 {
    private var answer = Int.MIN_VALUE

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        dfs(0, k, BooleanArray(dungeons.size), dungeons)
        return answer
    }

    private fun dfs(depth: Int, n: Int, visited: BooleanArray, dungeons: Array<IntArray>) {
        for (i in dungeons.indices) {
            val (require, cost) = dungeons[i]

            if (!visited[i] && require <= n && n - cost >= 0) {
                visited[i] = true
                dfs(depth + 1, n - cost, visited, dungeons)
                visited[i] = false
            }
        }
        answer = answer.coerceAtLeast(depth)
    }
}

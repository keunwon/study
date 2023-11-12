package com.keunwon.algorithm.againresolve

class ALessons87946 {
    private var answer = 0

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        dfs(0, k, BooleanArray(dungeons.size), dungeons)
        return answer
    }

    private fun dfs(depth: Int, k: Int, visited: BooleanArray, dungeons: Array<IntArray>) {
        for (i in dungeons.indices) {
            val (required, cost) = dungeons[i]

            if (!visited[i] && k >= required && k - cost >= 0) {
                visited[i] = true
                dfs(depth + 1, k - cost, visited, dungeons)
                visited[i] = false
            }
        }
        answer = answer.coerceAtLeast(depth)
    }
}

fun main() {
    ALessons87946().solution(
            80,
            arrayOf(
                    intArrayOf(80, 20),
                    intArrayOf(50, 40),
                    intArrayOf(30, 10)
            )
    ).also(::println)
}

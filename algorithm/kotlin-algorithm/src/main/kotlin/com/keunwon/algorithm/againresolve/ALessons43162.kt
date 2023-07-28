package com.keunwon.algorithm.againresolve

class ALessons43162 {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visited = BooleanArray(n)

        for (i in visited.indices) {
            if (!visited[i]) {
                dfs(i, computers, visited)
                answer++
            }
        }
        return answer
    }

    private fun dfs(n: Int, computers: Array<IntArray>, visited: BooleanArray) {
        visited[n] = true
        for (i in visited.indices) {
            if (!visited[i] && computers[n][i] == 1) {
                dfs(i, computers, visited)
            }
        }
    }
}

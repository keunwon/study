package com.keunwon.algorithm.programmers

/**
 * Title: 등대
 * Level: 3
 **/
class Lessons133500 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray
    private var answer = 0

    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            lighthouse.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        this.visited = BooleanArray(n + 1)

        dfs(1, -1)
        return answer
    }

    private fun dfs(cur: Int, prev: Int) {
        for (next in graph[cur]) {
            if (next == prev) continue
            dfs(next, cur)
            if (!visited[cur] && !visited[next]) {
                visited[cur] = true
                answer++
            }
        }
    }
}

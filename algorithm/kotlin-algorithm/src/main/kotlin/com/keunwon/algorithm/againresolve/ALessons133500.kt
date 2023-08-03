package com.keunwon.algorithm.againresolve

class ALessons133500 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var visited: BooleanArray

    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        this.graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            lighthouse.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }
        this.visited = BooleanArray(n + 1)

        dfs(1, -1)
        return visited.count { it }
    }

    private fun dfs(cur: Int, prev: Int) {
        for (next in graph[cur]) {
            if (next == prev) continue
            dfs(next, cur)
            if (!visited[cur] && !visited[next]) {
                visited[cur] = true
            }
        }
    }
}

fun main() {
    val n = 8
    val lighthouse = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 4),
        intArrayOf(1, 5),
        intArrayOf(5, 6),
        intArrayOf(5, 7),
        intArrayOf(5, 8),
    )
    ALessons133500().solution(n, lighthouse).also { println(it) }
}

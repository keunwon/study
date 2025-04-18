package algorithm.programmers

class Lesson133500 {
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
            if (!visited[next] && !visited[cur]) {
                visited[cur] = true
            }
        }
    }
}

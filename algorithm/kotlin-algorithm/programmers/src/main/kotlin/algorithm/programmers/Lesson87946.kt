package algorithm.programmers

import kotlin.math.max

class Lesson87946 {
    private var answer = 0

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        dfs(k, dungeons, BooleanArray(dungeons.size))
        return answer
    }

    private fun dfs(k: Int, dungeons: Array<IntArray>, visited: BooleanArray) {
        answer = max(answer, visited.count { it })

        for ((i, dungeon) in dungeons.withIndex()) {
            val (required, consume) = dungeon

            if (!visited[i] && required <= k) {
                visited[i] = true
                dfs(k - consume, dungeons, visited)
                visited[i] = false
            }
        }
    }
}

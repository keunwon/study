package algorithm.programmers

import java.util.PriorityQueue

class Lesson131130 {
    fun solution(cards: IntArray): Int {
        val q = PriorityQueue<Int>(compareByDescending { it })
        val visited = BooleanArray(cards.size)

        for (i in cards.indices) {
            if (!visited[i]) q.offer(dfs(cards, visited, i, 0))
        }

        return if (q.size > 1) q.poll() * q.poll() else 0
    }

    private fun dfs(
        cards: IntArray,
        visited: BooleanArray,
        cur: Int,
        depth: Int,
    ): Int {
        if (visited[cur]) return depth

        visited[cur] = true
        return dfs(cards, visited, cards[cur] - 1, depth + 1)
    }
}

package algorithm.programmers

import java.util.*

class Lesson131130 {
    private lateinit var cards: IntArray
    private lateinit var visited: BooleanArray

    private val queue = PriorityQueue<Int>(compareByDescending { it })

    fun solution(cards: IntArray): Int {
        this.cards = intArrayOf(0, *cards)
        this.visited = BooleanArray(this.cards.size)

        for (i in 1..cards.size) {
            if (!visited[i]) dfs(0, i)
        }
        return if (queue.size < 2) 0 else queue.poll() * queue.poll()
    }

    private fun dfs(count: Int, cur: Int) {
        if (visited[cur]) {
            queue.offer(count)
            return
        }

        visited[cur] = true
        dfs(count + 1, cards[cur])
    }
}

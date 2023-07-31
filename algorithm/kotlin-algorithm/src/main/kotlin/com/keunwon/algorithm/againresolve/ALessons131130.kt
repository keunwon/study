package com.keunwon.algorithm.againresolve

class ALessons131130 {
    private lateinit var cards: IntArray
    private lateinit var visited: BooleanArray

    private val queue = mutableListOf<Int>()

    fun solution(cards: IntArray): Int {
        this.cards = cards
        this.visited = BooleanArray(cards.size + 1)

        for (i in 1..cards.size) {
            if (!visited[i]) dfs(i, 0)
        }
        queue.sortDescending()
        return if (queue.size < 2) 0 else queue[0] * queue[1]
    }

    private fun dfs(index: Int, count: Int) {
        if (visited[index]) {
            queue.add(count)
            return
        }

        visited[index] = true
        dfs(cards[index - 1], count + 1)
    }
}

fun main() {
    val cards = intArrayOf(8, 6, 3, 7, 2, 5, 1, 4)
    ALessons131130().solution(cards).also { println(it) }
}

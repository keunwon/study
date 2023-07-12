package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 혼자 놀기의 달인
 * Level: 2
 **/
class Lessons131130 {
    private lateinit var cards: IntArray
    private lateinit var visited: BooleanArray

    private val queue = PriorityQueue<Int>(compareByDescending { it })

    fun solution(cards: IntArray): Int {
        this.cards = cards
        this.visited = BooleanArray(cards.size + 1)

        for (i in 1..cards.size) {
            if (!visited[i]) dfs(0, i)
        }
        return if (queue.size < 2) 0 else queue.poll() * queue.poll()
    }

    private fun dfs(depth: Int, n: Int) {
        if (visited[n]) {
            queue.offer(depth)
            return
        }
        visited[n] = true
        dfs(depth + 1, cards[n - 1])
    }
}

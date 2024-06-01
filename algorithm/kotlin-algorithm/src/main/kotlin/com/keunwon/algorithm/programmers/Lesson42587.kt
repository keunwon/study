package com.keunwon.algorithm.programmers

import java.util.*

class Lesson42587 {
    fun solution(priorities: IntArray, location: Int): Int {
        val deque = ArrayDeque<Node>().apply {
            priorities.mapIndexed { index, p -> offer(Node(index, p)) }
        }
        var order = 0

        while (deque.isNotEmpty()) {
            val max = deque.maxOf { it.priority }
            val cur = deque.poll()

            if (max == cur.priority) {
                ++order
                if (cur.index == location) break
            } else {
                deque.offer(cur)
            }
        }
        return order
    }

    private data class Node(val index: Int, val priority: Int)
}

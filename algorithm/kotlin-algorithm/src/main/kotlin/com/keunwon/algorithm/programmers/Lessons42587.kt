package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 프로세스
 * Level: 2
 **/
class Lessons42587 {
    fun solution(priorities: IntArray, location: Int): Int {
        val queue = ArrayDeque<Node>().apply {
            priorities.forEachIndexed { index, pri -> add(Node(index, pri)) }
        }

        var order = 0
        while (queue.isNotEmpty()) {
            val max = queue.maxOf { it.priority }
            val cur = queue.poll()

            if (max == cur.priority) {
                order++
                if (location == cur.index) break
            } else queue.add(cur)
        }
        return order
    }

    data class Node(val index: Int, val priority: Int)
}

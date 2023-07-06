package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 명예의 전당 (1)
 * Level: 1
 **/
class Lessons138477 {
    fun solution(k: Int, score: IntArray): IntArray {
        val queue = PriorityQueue<Int>()
        val answer = mutableListOf<Int>()

        for (num in score) {
            if (queue.size < k) {
                queue.add(num)
            } else if (queue.size == k && queue.peek() < num) {
                queue.poll()
                queue.add(num)
            }
            answer.add(queue.peek())
        }
        return answer.toIntArray()
    }
}

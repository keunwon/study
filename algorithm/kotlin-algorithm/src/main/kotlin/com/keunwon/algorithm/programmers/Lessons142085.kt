package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 디펜스 게임
 * Level: 2
 **/
class Lessons142085 {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        val queue = PriorityQueue<Int>()
        var ret = n

        for ((index, people) in enemy.withIndex()) {
            queue.offer(people)

            if (k < queue.size) ret -= queue.poll()
            if (ret < 0) return index
        }
        return enemy.size
    }
}

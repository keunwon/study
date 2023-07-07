package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 문자열 나누기
 * Level: 1
 **/
class Lessons140108 {
    fun solution(s: String): Int {
        var answer = 0
        val queue = LinkedList<Char>()

        for (w in s) {
            if (queue.isEmpty()) {
                queue.offer(w)
                answer++
            } else if (queue.peek() == w) {
                queue.offer(w)
            } else {
                queue.poll()
            }
        }
        return answer
    }
}

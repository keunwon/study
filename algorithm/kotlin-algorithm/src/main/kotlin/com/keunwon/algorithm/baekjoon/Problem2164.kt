package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 카드2
 * Level: 실버-4
 **/
class Problem2164 {
    fun solution(n: Int): Int {
        val queue = LinkedList<Int>().apply { repeat(n) { add(it + 1) } }

        while (queue.size > 1) {
            queue.poll()
            queue.offer(queue.poll())
        }
        return queue.first
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2164().solution(n).also { println(it) }
}

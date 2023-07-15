package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 최소 힙
 * Level: 실버-2
 **/
class Problem1927 {
    fun solution(arr: IntArray): IntArray {
        val queue = PriorityQueue<Int>()
        val answer = mutableListOf<Int>()

        for (n in arr) {
            if (n == 0) {
                val tmp = if (queue.isEmpty()) 0 else queue.poll()
                answer.add(tmp)
                continue
            }
            queue.offer(n)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem1927().solution(arr).also { println(it.joinToString(System.lineSeparator())) }
}

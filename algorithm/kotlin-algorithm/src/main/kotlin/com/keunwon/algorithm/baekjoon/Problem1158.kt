package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 요세푸스
 * Level: 실버-4
 **/
class Problem1158 {
    fun solution(n: Int, k: Int): String {
        val queue = LinkedList<Int>().apply { repeat(n) { offer(it + 1) } }
        val answer = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            repeat(k - 1) { queue.offer(queue.poll()) }
            answer.add(queue.poll())
        }
        return "<${answer.joinToString(", ")}>"
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    Problem1158().solution(n, k).also { println(it) }
}

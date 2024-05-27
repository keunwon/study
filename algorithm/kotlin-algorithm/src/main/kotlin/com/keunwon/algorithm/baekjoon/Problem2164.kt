package com.keunwon.algorithm.baekjoon

import java.util.LinkedList

class Problem2164 {
    fun solution(n: Int): Int {
        val queue = LinkedList<Int>().apply { (1..n).forEach { offer(it) } }

        while (queue.size > 1) {
            queue.poll()
            queue.offer(queue.poll())
        }
        return queue.peek()
    }
}

fun main() {
    val n = readln().toInt()
    println(Problem2164().solution(n));
}

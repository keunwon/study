package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1927 {
    fun solution(numbers: IntArray): IntArray {
        val queue = PriorityQueue<Int>()
        val answer = mutableListOf<Int>()

        for (n in numbers) {
            if (n == 0) {
                answer.add(if (queue.isNotEmpty()) queue.poll() else 0)
            } else {
                queue.offer(n)
            }
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readln().toInt()
    val numbers = IntArray(n) { readln().toInt() }

    Problem1927().solution(numbers).also { println(it.joinToString(System.lineSeparator())) }
}

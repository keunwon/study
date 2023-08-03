package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 프린터 큐
 * Level: 실버-3
 **/
class Problem1966 {
    fun solution(n: Int, m: Int, ranks: IntArray): Int {
        val rankQueue = PriorityQueue<Int>(compareByDescending { it })
        val indexQueue = LinkedList<Int>()
        var answer = 0

        ranks.forEachIndexed { index, rank ->
            rankQueue.offer(rank)
            indexQueue.offer(index)
        }

        while (indexQueue.isNotEmpty()) {
            val max = rankQueue.poll()
            answer++

            while (ranks[indexQueue.peek()] != max) {
                indexQueue.offer(indexQueue.poll())
            }
            if (indexQueue.poll() == m) return answer
        }
        return answer
    }
}

fun main() {
    val t = readLine()!!.toInt()

    repeat(t) {
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        Problem1966().solution(n, m, arr).also { println(it) }
    }
}

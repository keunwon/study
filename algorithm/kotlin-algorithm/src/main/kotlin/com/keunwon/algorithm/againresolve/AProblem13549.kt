package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 숨바꼭질 3
 * Level: 골드-5
 **/
class AProblem13549 {
    fun solution(n: Int, k: Int): Int {
        val queue = LinkedList<Node>()
        val dp = IntArray(INF) { INF }

        queue.offer(Node(n, 0))
        dp[n] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (dp[cur.index] < cur.time) continue

            val nums = intArrayOf(cur.index * 2, cur.index + 1, cur.index - 1)
            val times = intArrayOf(cur.time, cur.time + 1, cur.time + 1)

            for (i in 0 until 3) {
                val num = nums[i]
                val time = times[i]

                if (num !in 0 until INF) continue

                if (dp[num] > time) {
                    dp[num] = time
                    queue.offer(Node(num, time))
                }
            }
        }
        return dp[k]
    }

    private data class Node(val index: Int, val time: Int)

    companion object {
        private const val INF = 100_001
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    AProblem13549().solution(n, k).also(::println)
}

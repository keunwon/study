package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 숨바꼭질 3
 * Level: 골드-5
 **/
class Problem13549 {
    fun solution(n: Int, k: Int): Int {
        return bfs(n, k)
    }

    private fun bfs(n: Int, k: Int): Int {
        var answer = 100_001
        val visited = BooleanArray(100_001)
        val queue = LinkedList<IntArray>().apply { offer(intArrayOf(n, 0)) }

        while (queue.isNotEmpty()) {
            val (cur, second) = queue.poll()
            visited[cur] = true

            if (cur == k) {
                answer = answer.coerceAtMost(second)
                continue
            }

            val nums = intArrayOf(cur + 1, cur - 1, cur * 2)
            val seconds = intArrayOf(second + 1, second + 1, second)

            for (i in 0 until 3) {
                val num = nums[i]
                if (num in 0..100_000 && !visited[num]) {
                    queue.offer(intArrayOf(num, seconds[i]))
                }
            }
        }
        return answer
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    Problem13549().solution(n, k).also { println(it) }
}

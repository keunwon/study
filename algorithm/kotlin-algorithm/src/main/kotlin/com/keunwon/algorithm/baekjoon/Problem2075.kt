package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: N번째 큰 수
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2075">N번째 큰 수 (실버-3)</a>
 **/
class Problem2075 {
    fun solution(board: Array<IntArray>): Int {
        val q = PriorityQueue<Int>()
        val n = board.size

        for (i in board.indices) {
            for (num in board[i]) {
                if (q.size >= n) {
                    if (num > q.peek()) {
                        q.poll()
                        q.offer(num)
                    }
                } else {
                    q.offer(num)
                }
            }
        }
        return q.peek()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val board = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }

    Problem2075().solution(board).also { println(it) }
}

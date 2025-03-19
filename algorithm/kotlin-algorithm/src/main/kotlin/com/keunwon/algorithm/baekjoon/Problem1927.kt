package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 최소 힙
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1927">최소 힙 (실버-2)</a>
 **/
class Problem1927 {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        var n = br.readLine().toInt()
        val q = PriorityQueue<Int>(n)

        while (n-- > 0) {
            val x = br.readLine().toInt()

            if (x == 0) {
                bw.write(if (q.isEmpty()) "0" else "${q.poll()}")
                bw.newLine()
            } else {
                q.offer(x)
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }
}

fun main() {
    Problem1927().solution()
}

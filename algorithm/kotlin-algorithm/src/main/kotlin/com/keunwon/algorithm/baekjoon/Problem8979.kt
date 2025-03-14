package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 올림픽
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/8979">올림픽 (실버-5)</a>
 **/
class Problem8979 {
    fun solution(k: Int, infos: Array<IntArray>): Int {
        infos.sortWith(compareBy({ -it[1] }, { -it[2] }, { -it[3] }))

        if (infos[0][0] == k) return 1

        var rank = 1
        var step = 1

        for (i in 1 until infos.size) {
            val pre = infos[i - 1]
            val cur = infos[i]

            if (pre[1] == cur[1] && pre[2] == cur[2] && pre[3] == cur[3]) {
                ++step
            } else {
                rank += step
                step = 1
            }

            if (cur[0] == k) break
        }
        return rank
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val infos = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(4) { arr[it] }
    }

    Problem8979().solution(k, infos).also { println(it) }
}

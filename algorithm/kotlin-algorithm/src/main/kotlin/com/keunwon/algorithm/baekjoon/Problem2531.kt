package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 회전 초밥
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2531">회전 초밥 (실버-1)</a>
 **/
class Problem2531 {
    fun solution(d: Int, k: Int, c: Int, types: IntArray): Int {
        val visited = IntArray(d + 1)
        var count = 0
        var result = 0

        for (i in 0 until k) {
            val type = types[i]
            if (visited[type] == 0) ++count
            ++visited[type]
        }

        for (i in types.indices) {
            if (result <= count) {
                result = count + if (visited[c] == 0) 1 else 0
            }

            val s = types[i]
            --visited[s]
            if (visited[s] == 0) --count

            val e = types[(i + k) % types.size]
            if (visited[e] == 0) ++count
            ++visited[e]
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, d, k, c) = br.readLine().split(" ").map { it.toInt() }
    val types = IntArray(n) { br.readLine().toInt() }

    Problem2531().solution(d, k, c, types).also { println(it) }
}

package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 지름길
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1446">지름길 (실버-1)</a>
 **/
class Problem1446 {
    fun solution(dest: Int, routes: Array<IntArray>): Int {
        routes.sortWith(compareBy({ it[0] }, { it[1] }))

        val dist = IntArray(10_001) { 1e9.toInt() }.apply { this[0] = 0 }
        var cur = 0
        var index = 0

        while (cur < dest) {
            if (index == routes.size) {
                ++cur
                dist[cur] = dist[cur].coerceAtMost(dist[cur - 1] + 1)
                continue
            }

            val (s, e, d) = routes[index]
            if (cur == s) {
                dist[e] = dist[e].coerceAtMost(dist[s] + d)
                ++index
            } else {
                ++cur
                dist[cur] = dist[cur].coerceAtMost(dist[cur - 1] + 1)
            }
        }
        return dist[dest]
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, d) = br.readLine().split(" ").map { it.toInt() }
    val routes = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(3) { arr[it] }
    }

    Problem1446().solution(d, routes).also { println(it) }
}

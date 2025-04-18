package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 파티
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1238">파티 (골드-3)</a>
 **/
class Problem1238 {
    fun solution(n: Int, x: Int, edges: Array<IntArray>): Int {
        val dist = Array(n + 1) { IntArray(n + 1) { 1e9.toInt() } }.apply {
            repeat(n) { this[it][it] = 0 }
            edges.forEach { (s, e, t) -> this[s][e] = this[s][e].coerceAtMost(t) }
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    dist[j][k] = dist[j][k].coerceAtMost(dist[j][i] + dist[i][k])
                }
            }
        }
        return (1..n).maxOf { dist[it][x] + dist[x][it] }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, m, x) = br.readLine().split(" ").map { it.toInt() }
    val edges = Array(m) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        IntArray(3) { arr[it] }
    }
    Problem1238().solution(n, x, edges).also { println(it) }

    br.close()
}

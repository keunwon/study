package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 서강그라운드
 * Level: 골드-4
 **/
class Problem14938 {
    fun solution(n: Int, m: Int, points: IntArray, edges: Array<Triple<Int, Int, Int>>): Int {
        val dist = Array(n + 1) { IntArray(n + 1) { 1e9.toInt() } }

        repeat(n) { dist[it + 1][it + 1] = 0 }
        edges.forEach { (a, b, c) ->
            dist[a][b] = c
            dist[b][a] = c
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    val distance = dist[j][i] + dist[i][k]
                    if (dist[j][k] > distance) {
                        dist[j][k] = distance
                    }
                }
            }
        }
        return dist.maxOf { arr ->
            (1..n).sumOf { i -> if (arr[i] <= m) points[i - 1] else 0 }
        }
    }
}

fun main() {
    val (n, m, r) = readLine()!!.split(" ").map { it.toInt() }
    val points = run {
        val st = StringTokenizer(readLine()!!)
        IntArray(n) { st.nextToken().toInt() }
    }
    val edges = Array(r) {
        val st = StringTokenizer(readLine()!!)
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    Problem14938().solution(n, m, points, edges).also(::println)
}

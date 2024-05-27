package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.min

class Problem1446 {
    fun solution(d: Int, loads: Array<IntArray>): Int {
        val dist = IntArray(d + 1) { 1e9.toInt() }.apply { this[0] = 0 }
        loads.sortWith(compareBy({ it[0] }, { it[1] }, { it[2] }))

        for (i in 0 until d) {
            dist[i + 1] = min(dist[i + 1], dist[i] + 1)

            for ((s, e, n) in loads) {
                if (i == s && e in 0..d) {
                    dist[e] = min(dist[e], dist[s] + n)
                }
            }
        }
        return dist[d]
    }
}

fun main() {
    val (n, d) = readln().split(" ").map { it.toInt() }
    val loads = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(3) { st.nextToken().toInt() }
    }

    println(Problem1446().solution(d, loads))
}

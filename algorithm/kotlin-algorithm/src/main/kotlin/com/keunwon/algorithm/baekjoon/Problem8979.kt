package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem8979 {
    fun solution(k: Int, medals: Array<IntArray>): Int {
        medals.sortWith(compareBy({ -it[1] }, { -it[2] }, { -it[3] }))

        if (medals[0][0] == k) return 1

        var rank = 1
        var step = 1

        for (i in 1 until medals.size) {
            val pre = medals[i - 1]
            val cur = medals[i]

            if (pre.slice(1..3) == cur.slice(1..3)) {
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
    val (n, k) = readln().split(" ").map { it.toInt() }
    val medals = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(4) { st.nextToken().toInt() }
    }

    println(Problem8979().solution(k, medals))
}

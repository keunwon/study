package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem13305 {
    fun solution(distances: LongArray, oils: LongArray): Long {
        var answer = 0L
        var minOil = oils[0]

        for (i in distances.indices) {
            if (minOil > oils[i]) {
                minOil = oils[i]
            }
            answer += distances[i] * minOil
        }
        return answer
    }
}

fun main() {
    val n = readln().toInt()
    val distances = run {
        val st = StringTokenizer(readln())
        LongArray(n - 1) { st.nextToken().toLong() }
    }
    val oils = run {
        val st = StringTokenizer(readln())
        LongArray(n) { st.nextToken().toLong() }
    }

    println(Problem13305().solution(distances, oils))
}

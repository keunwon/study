package com.keunwon.algorithm.againresolve

/**
 * Title: 출석체크
 * Level: 실버-2
 **/
class Problem20438 {
    fun solution(n: Int, sleep: IntArray, attendances: IntArray, rages: Array<IntArray>): IntArray {
        val prefixSum = IntArray(n + 3) { 1 }

        repeat(3) { prefixSum[it] = 0 }

        for (at in attendances) {
            if (at in sleep) continue

            for (i in at until prefixSum.size step at) {
                if (i !in sleep) prefixSum[i] = 0
            }
        }

        for (i in 4 until prefixSum.size) {
            prefixSum[i] += prefixSum[i - 1]
        }

        return rages.map { (s, e) -> prefixSum[e] - prefixSum[s - 1] }
            .toIntArray()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val readIntArray = {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    val (n, k, q, m) = readIntArray()
    val sleep = readIntArray()
    val attendances = readIntArray()
    val ranges = Array(m) { readIntArray() }

    Problem20438().solution(n, sleep, attendances, ranges).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}

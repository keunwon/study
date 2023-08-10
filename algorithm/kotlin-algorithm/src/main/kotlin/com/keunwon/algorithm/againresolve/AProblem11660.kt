package com.keunwon.algorithm.againresolve

class AProblem11660 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): IntArray {
        val prefixSum = Array(arr1.size + 1) { IntArray(arr1[0].size + 1) }

        for (i in 1 until prefixSum.size) {
            for (j in 1 until prefixSum[0].size) {
                prefixSum[i][j] =
                    prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + arr1[i - 1][j - 1]
            }
        }

        return arr2.map { (r1, c1, r2, c2) ->
            val tmp = prefixSum[r1 - 1][c2] + prefixSum[r2][c1 - 1] - prefixSum[r1 - 1][c1 - 1]
            prefixSum[r2][c2] - tmp
        }.toIntArray()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val readIntArray = {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr1 = Array(n) { readIntArray() }
    val arr2 = Array(m) { readIntArray() }

    AProblem11660().solution(arr1, arr2).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}


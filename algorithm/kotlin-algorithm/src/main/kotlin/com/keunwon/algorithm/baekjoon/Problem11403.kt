package com.keunwon.algorithm.baekjoon

/**
 * Title: 경로 찾기
 * Level: 실버-1
 **/
class Problem11403 {
    fun solution(arr: Array<IntArray>): Array<IntArray> {
        for (i in arr.indices) {
            for (j in arr.indices) {
                for (k in arr.indices) {
                    if (arr[j][i] == 1 && arr[i][k] == 1) {
                        arr[j][k] = 1
                    }
                }
            }
        }
        return arr
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem11403().solution(arr).forEach { println(it.joinToString(" ")) }
}

package com.keunwon.algorithm.againresolve

class ALessons12949 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) }

        for (i in answer.indices) {
            for (j in answer[0].indices) {
                for (k in arr1[0].indices) {
                    answer[i][j] += arr2[k][j] * arr1[i][k]
                }
            }
        }
        return answer
    }
}

fun main() {
    val arr1 = arrayOf(
        intArrayOf(2, 3, 2),
        intArrayOf(4, 2, 4),
        intArrayOf(3, 1, 4),
    )
    val arr2 = arrayOf(
        intArrayOf(5, 4, 3),
        intArrayOf(2, 4, 1),
        intArrayOf(3, 1, 1),
    )

    ALessons12949().solution(arr1, arr2).forEach { println(it.contentToString()) }
}

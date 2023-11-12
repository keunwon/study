package com.keunwon.algorithm.againresolve

class ALessons12949 {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr2[0].size) }
        for (i in arr1.indices) {
            for ((j, num1) in arr1[i].withIndex()) {
                for ((k, num2) in arr2[j].withIndex()) {
                    answer[i][k] += num1 * num2
                }
            }
        }
        return answer
    }
}

fun main() {
    ALessons12949().solution(
        arrayOf(intArrayOf(1, 4), intArrayOf(3, 2), intArrayOf(4, 1)),
        arrayOf(intArrayOf(3, 3), intArrayOf(3, 3))
    ).forEach { println(it.contentToString()) }

    ALessons12949().solution(
        arrayOf(intArrayOf(2, 3, 2), intArrayOf(4, 2, 4), intArrayOf(3, 1, 4)),
        arrayOf(intArrayOf(5, 4, 3), intArrayOf(2, 4, 1), intArrayOf(3, 1, 1))
    ).forEach { println(it.contentToString()) }
}

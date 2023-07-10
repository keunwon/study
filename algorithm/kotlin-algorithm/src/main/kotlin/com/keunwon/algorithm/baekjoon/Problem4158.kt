package com.keunwon.algorithm.baekjoon

/**
 * Title: CD
 * Level: 실버-5
 **/
class Problem4158 {
    fun solution(arr1: IntArray, arr2: IntArray): Int {
        var index1 = 0
        var index2 = 0
        var answer = 0

        while (index1 < arr1.size && index2 < arr2.size) {
            when {
                arr1[index1] > arr2[index2] -> index2++
                arr1[index1] < arr2[index2] -> index1++
                else -> {
                    index1++
                    index2++
                    answer++
                }
            }
        }
        return answer
    }
}

fun main() {
    while (true) {
        val (n, m) = readLine()!!.split(" ").map(String::toInt)
        if (n == 0 && m == 0) break

        val arr1 = IntArray(n) { readLine()!!.toInt() }
        val arr2 = IntArray(m) { readLine()!!.toInt() }

        Problem4158().solution(arr1, arr2).also { println(it) }
    }
}

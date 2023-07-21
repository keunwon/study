package com.keunwon.algorithm.baekjoon

/**
 * Title: 주몽
 * Level: 실버-4
 **/
class Problem1940 {
    fun solution(m: Int, arr: IntArray): Int {
        arr.sort()

        var start = 0
        var right = arr.lastIndex
        var answer = 0

        while (start < right) {
            val sum = arr[start] + arr[right]
            when {
                m > sum -> start++
                m < sum -> right--
                else -> {
                    start++
                    right--
                    answer++
                }
            }
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1940().solution(m, arr).also { println(it) }
}

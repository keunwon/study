package com.keunwon.algorithm.baekjoon

/**
 * Title: 최소 힙
 * Level: 실버-2
 **/
class Problem1927 {
    fun solution(arr: IntArray): IntArray {
        return intArrayOf()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem1927().solution(arr).also { println(it.joinToString(System.lineSeparator())) }
}

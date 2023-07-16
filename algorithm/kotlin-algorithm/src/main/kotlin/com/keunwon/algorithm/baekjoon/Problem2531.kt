package com.keunwon.algorithm.baekjoon

/**
 * Title: 회전 초밥
 * Level: 실버-1
 **/
class Problem2531 {
    fun solution(d: Int, k: Int, c: Int, arr: IntArray): Int {
        val visited = IntArray(d + 1)
        return 0
    }
}

fun main() {
    val (n, d, k, c) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }
    Problem2531().solution(d, k, c, arr).also { println(it) }
}

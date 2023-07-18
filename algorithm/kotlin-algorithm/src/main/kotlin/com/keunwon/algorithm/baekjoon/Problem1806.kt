package com.keunwon.algorithm.baekjoon

/**
 * Title: 부분합
 * Level: 골드-4
 **/
// todo 투-포인터
class Problem1806 {
    fun solution(s: Int, arr: IntArray): Int {
        return 0
    }
}

fun main() {
    val (n, s) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1806().solution(s, arr).also { println(it) }
}

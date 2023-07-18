package com.keunwon.algorithm.baekjoon

/**
 * Title: 겹치는 건 싫어
 * Level: 실버-1
 **/
// todo 투-포인터
class Problem20922 {
    fun solution(k: Int, numbers: IntArray): Int {
        return 0
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem20922().solution(k, numbers).also { println(it) }
}

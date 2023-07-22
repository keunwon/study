package com.keunwon.algorithm.baekjoon

/**
 * Title: 나머지 합
 * Level: 골드-3
 **/
// todo
class Problem10986 {
    fun solution(m: Int, arr: IntArray): Long {
        return 0L
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem10986().solution(m, arr).also { println(it) }
}

package com.keunwon.algorithm.baekjoon

/**
 * Title: 고충 건물
 * Level: 골드-4
 **/
class Problem1027 {
    fun solution(arr: IntArray): Int {
        return 0
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1027().solution(arr).also { println(it) }
}

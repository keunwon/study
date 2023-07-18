package com.keunwon.algorithm.baekjoon

/**
 * Title: 한 줄로 서기
 * Level: 실버-2
 **/
// todo
class Problem1138 {
    fun solution(arr: IntArray): IntArray {
        return intArrayOf()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1138().solution(arr).also { println(it.joinToString(" ")) }
}

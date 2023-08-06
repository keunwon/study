package com.keunwon.algorithm.baekjoon

/**
 * Title: 숫자의 합
 * Level: 브론즈-4
 **/
class Problem11720 {
    fun solution() {
        val n = readLine()!!.toInt()
        println(readLine()!!.map { it.digitToInt() }.sum())
    }
}

fun main() {
    Problem11720().solution()
}

package com.keunwon.algorithm.baekjoon

/**
 * Title: 행성 연결
 * Level: 골드-4
 **/
class Problem16398 {
    fun solution(arr: Array<IntArray>): Int {
        return 0
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem16398().solution(arr).also(::println)
}

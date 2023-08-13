package com.keunwon.algorithm.baekjoon

/**
 * Title: 외판원 순회2
 * Level: 실버-2
 **/
class Problem10971 {
    fun solution(n: Int, arr: Array<IntArray>): Int {
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

    Problem10971().solution(n, arr).also(::println)
}

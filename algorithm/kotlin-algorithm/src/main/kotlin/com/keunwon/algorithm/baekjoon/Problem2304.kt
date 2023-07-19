package com.keunwon.algorithm.baekjoon

/**
 * Title: 창고 다각형
 * Level: 실버-2
 **/
// todo
class Problem2304 {
    fun solution(arr: Array<Pair<Int, Int>>): Int {
        arr.sortBy { it.first }
        return 0
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem2304().solution(arr).also { println(it) }
}

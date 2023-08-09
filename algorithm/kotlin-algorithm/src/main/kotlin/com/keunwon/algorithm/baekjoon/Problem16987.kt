package com.keunwon.algorithm.baekjoon

/**
 * Title: 계란으로 계란치기
 * Level: 골드-5
 **/
class Problem16987 {
    fun solution(n: Int, arr: Array<Pair<Int, Int>>): Int {
        return 0
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = Array(n) {
        readLine().split(" ")
            .let { it[0].toInt() to it[1].toInt() }
    }

    Problem16987().solution(n, arr).also(::println)
}

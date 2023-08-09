package com.keunwon.algorithm.baekjoon

/**
 * Title: 궁금한 민호
 * Level: 골드-2
 **/
// todo
class Problem1507 {
    fun solution(n: Int, edges: Array<IntArray>): Int {
        val dist = Array(n) { IntArray(n) { INF } }
        return 0
    }

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val edges = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem1507().solution(n, edges).also(::println)
}

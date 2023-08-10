package com.keunwon.algorithm.baekjoon

/**
 * Title: 외판원 순회2
 * Level: 실버-2
 **/
class Problem10971(val n: Int, val arr: Array<IntArray>) {
    fun solution(): Int {
        return 0
    }

    companion object {
        private const val INF = 1e9.toInt()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem10971(n, arr).solution().also(::println)
}

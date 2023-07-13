package com.keunwon.algorithm.baekjoon

/**
 * Title: ZOAC 4
 * Level: 브론즈-3
 **/
class Problem23971 {
    fun solution(h: Int, w: Int, n: Int, m: Int): Int {
        val height = (h - 1) / (n + 1) + 1
        val weight = (w - 1) / (m + 1) + 1
        return height * weight
    }
}

fun main() {
    val (h, w, n, m) = readLine()!!.split(" ").map { it.toInt() }
    Problem23971().solution(h, w, n, m).also { println(it) }
}

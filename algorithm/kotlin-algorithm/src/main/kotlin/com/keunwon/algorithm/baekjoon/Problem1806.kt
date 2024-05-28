package com.keunwon.algorithm.baekjoon

import java.util.*

// todo
class Problem1806 {
    fun solution(s: Int, arr: IntArray): Int {
        return 0
    }
}

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }
    println(Problem1806().solution(s, arr))
}

package com.keunwon.algorithm.baekjoon

import java.util.*

// todo
class Problem1205 {
    fun solution(target: Int, p: Int, numbers: IntArray): Int {
        return 0
    }
}

fun main() {
    val (n, target, p) = readln().split(" ").map { it.toInt() }
    val numbers = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem1205().solution(target, p, numbers))
}

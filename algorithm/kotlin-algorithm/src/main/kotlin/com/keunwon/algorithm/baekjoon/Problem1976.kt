package com.keunwon.algorithm.baekjoon

import java.util.*

// todo
class Problem1976 {
    fun solution(n: Int, plans: Array<IntArray>): String {

        return ""
    }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val plans = Array(m) {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }
    val target = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem1976().solution(n, plans))
}

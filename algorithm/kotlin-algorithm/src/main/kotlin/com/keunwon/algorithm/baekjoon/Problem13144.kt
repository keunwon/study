package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: List of Unique Numbers
 * Level: 골드-4
 **/
// todo
class Problem13144 {
    fun solution(arr: IntArray): Long {
        return 0L
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val st = StringTokenizer(readLine()!!)
    val arr = IntArray(n) { st.nextToken().toInt() }

    Problem13144().solution(arr).also(::println)
}

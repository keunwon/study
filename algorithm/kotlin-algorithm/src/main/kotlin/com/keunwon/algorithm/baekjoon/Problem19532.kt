package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 수학은 비대면강의입니다.
 * Level: 브론즈-2
 **/
class Problem19532 {
    fun solution(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int): Pair<Int, Int> {
        for (i in -999..999) {
            for (j in -999..999) {
                if (a * i + b * j == c && d * i + e * j == f) return i to j
            }
        }
        error("")
    }
}

fun main() {
    fun StringTokenizer.nextInt(): Int = this.nextToken().toInt()

    val st = StringTokenizer(readLine()!!)
    val a = st.nextInt()
    val b = st.nextInt()
    val c = st.nextInt()
    val d = st.nextInt()
    val e = st.nextInt()
    val f = st.nextInt()

    Problem19532().solution(a, b, c, d, e, f).also { println("${it.first} ${it.second}") }
}

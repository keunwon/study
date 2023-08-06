package com.keunwon.algorithm.baekjoon

/**
 * Title: 알람 시계
 * Level: 브론즈-3
 **/
class Problem2884 {
    fun solution(h: Int, m: Int): Pair<Int, Int> {
        val tmp = m - 45

        if (tmp >= 0) return h to tmp

        val hour = if (h == 0) 23 else h - 1
        val minute = 60 + tmp
        return hour to minute
    }
}

fun main() {
    val (h, m) = readLine()!!.split(" ").map { it.toInt() }
    Problem2884().solution(h, m).also {
        println("${it.first} ${it.second}")
    }
}

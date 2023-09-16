package com.keunwon.algorithm.baekjoon

/**
 * Title: 윤년
 * Level: 브론즈-5
 **/
class Problem2753 {
    fun solution(n: Int): Int {
        val leapYear = (n % 4 == 0) && (n % 100 != 0 || n % 400 == 0)
        return if (leapYear) 1 else 0
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2753().solution(n).also { println(it) }
}

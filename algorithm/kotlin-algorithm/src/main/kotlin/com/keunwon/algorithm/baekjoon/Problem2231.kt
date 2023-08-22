package com.keunwon.algorithm.baekjoon

/**
 * Title: 분해합
 * Level: 브론즈-2
 **/
class Problem2231 {
    fun solution(n: Int): Int {
        for (i in 1..n) {
            val sum = i + "$i".toCharArray().sumOf { it.digitToInt() }
            if (sum == n) return i
        }
        return 0
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2231().solution(n).also(::println)
}

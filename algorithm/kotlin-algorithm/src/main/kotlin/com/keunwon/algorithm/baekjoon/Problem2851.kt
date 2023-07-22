package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

/**
 * Title: 슈퍼 마리오
 * Level: 브론즈-1
 **/
class Problem2851 {
    fun solution(arr: IntArray): Int {
        var diff = 100
        var answer = 0
        var sum = 0

        for (n in arr) {
            sum += n
            if (abs(100 - sum) < diff) {
                diff = abs(100 - sum)
                answer = sum
            }
        }
        return answer
    }
}

fun main() {
    val arr = IntArray(10) { readLine()!!.toInt() }
    Problem2851().solution(arr).also { println(it) }
}

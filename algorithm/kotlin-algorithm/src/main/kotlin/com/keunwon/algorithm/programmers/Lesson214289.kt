package com.keunwon.algorithm.programmers

class Lesson214289 {
    fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
        val temp = temperature + 10
        var tt1 = t1 + 10
        var tt2 = t2 + 10
        val inf = 1e9.toInt()
        val dp = Array(onboard.size) { IntArray(51) { inf } }.apply { this[0][temp] = 0 }
        return 0
    }
}

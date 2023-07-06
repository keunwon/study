package com.keunwon.algorithm.programmers

/**
 * Title: 2016년
 * Level: 1
 **/
class Lessons12901 {
    fun solution(a: Int, b: Int): String {
        val days = arrayOf("FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU")
        val month = intArrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30)
        val n = month.slice(0 until a).sum() + b - 1
        return days[n % 7]
    }
}

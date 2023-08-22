package com.keunwon.algorithm.baekjoon

/**
 * Title: 피로도
 * Level: 브론즈-2
 **/
class Problem22864 {
    fun solution(a: Int, b: Int, c: Int, m: Int): Int {
        var fatigue = 0
        var answer = 0
        var hour = 0

        while (hour < 24) {
            if (fatigue + a > m) {
                fatigue -= c
                if (fatigue < 0) fatigue = 0
                hour++
                continue
            }

            fatigue += a
            answer += b
            hour++
        }
        return answer
    }
}

fun main() {
    val (a, b, c, m) = readLine()!!.split(" ").map { it.toInt() }
    Problem22864().solution(a, b, c, m).also(::println)
}

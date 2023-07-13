package com.keunwon.algorithm.baekjoon

/**
 * Title: 삼각형과 세 변
 * Level: 브로즌-3
 **/
class Problem5073 {
    fun solution(arr: IntArray): String {
        arr.sort()
        if (arr[2] >= arr[0] + arr[1]) return "Invalid"
        return when (arr.toSet().size) {
            1 -> "Equilateral"
            2 -> "Isosceles"
            3 -> "Scalene"
            else -> error("")
        }
    }
}

fun main() {
    while (true) {
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        if (arr.all { it == 0 }) break

        Problem5073().solution(arr).also { println(it) }
    }
}

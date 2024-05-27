package com.keunwon.algorithm.baekjoon

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
        val arr = readln().split(" ").map { it.toInt() }.toIntArray()

        if (arr.all { it == 0 }) break
        println(Problem5073().solution(arr))
    }
}

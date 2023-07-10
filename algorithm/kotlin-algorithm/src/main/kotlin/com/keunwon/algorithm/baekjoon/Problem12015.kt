package com.keunwon.algorithm.baekjoon

/**
 * Title: 가장 긴 증가하는 부분 수열 2
 * Level: 골드-5
 **/
// todo
class Problem12015 {
    fun solution(arr: IntArray): Int {
        val dp = mutableListOf<Int>()

        for (num in arr) {
            if (dp.isEmpty() || dp.last() < num) {
                dp.add(num)
                continue
            }

        }
        return 0
    }
}

fun main() {
    readLine()!!
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem12015().solution(arr).also { println(it) }
}

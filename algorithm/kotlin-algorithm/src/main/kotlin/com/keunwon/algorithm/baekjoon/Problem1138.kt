package com.keunwon.algorithm.baekjoon

/**
 * Title: 한 줄로 서기
 * Level: 실버-2
 **/
class Problem1138 {
    fun solution(arr: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        for (i in arr.size downTo 1) {
            answer.add(arr[i - 1], i)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1138().solution(arr).also { println(it.joinToString(" ")) }
}

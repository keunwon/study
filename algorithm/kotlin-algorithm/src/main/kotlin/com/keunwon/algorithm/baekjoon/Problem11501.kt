package com.keunwon.algorithm.baekjoon

/**
 * Title: 주식
 * Level: 실버-2
 **/
class Problem11501 {
    fun solution(arr: IntArray): Long {
        var answer = 0L
        var max = 0L

        for (i in arr.lastIndex downTo 0) {
            if (arr[i] > max) max = arr[i].toLong()
            else answer += max - arr[i]
        }
        return answer
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        Problem11501().solution(arr).also { println(it) }
    }
}

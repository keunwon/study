package com.keunwon.algorithm.baekjoon

/**
 * Title: 줄세우기
 * Level: 실버-5
 **/
class Problem10431 {
    fun solution(arr: IntArray): Int {
        var count = 0
        for (i in 1 until arr.size) {
            for (j in i - 1 downTo 0) {
                if (arr[i] < arr[j]) count++
            }
        }
        return count
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) { idx ->
        val arr = readLine()!!.split(" ")
            .map { it.toInt() }
            .let { it.subList(1, it.size) }
            .toIntArray()
        Problem10431().solution(arr).also { println("${idx + 1} $it") }
    }
}

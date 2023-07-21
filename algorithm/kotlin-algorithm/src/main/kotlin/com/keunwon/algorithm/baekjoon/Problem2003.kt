package com.keunwon.algorithm.baekjoon

/**
 * Title: 수들의 합 2
 * Level: 실버-4
 **/
class Problem2003 {
    fun solution(m: Int, arr: IntArray): Int {
        var start = 0
        var end = 0
        var sum = 0
        var answer = 0

        while (start < arr.size) {
            if (m < sum || end == arr.size) sum -= arr[start++]
            else sum += arr[end++]

            if (sum == m) answer++
        }
        return answer
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    Problem2003().solution(m, arr).also { println(it) }
}

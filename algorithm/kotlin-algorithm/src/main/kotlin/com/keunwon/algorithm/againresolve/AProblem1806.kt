package com.keunwon.algorithm.againresolve

class AProblem1806 {
    fun solution(s: Int, arr: IntArray): Int {
        var (left, right) = 0 to 0
        var answer = 100_001
        var total = 0

        while (right < arr.size) {
            if (s <= total) {
                answer = answer.coerceAtMost(right - left)
                total -= arr[left++]
            } else total += arr[right++]
        }
        return if (answer == 100_001) 0 else answer
    }
}

fun main() {
    val (n, s) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.plus(0).toIntArray()

    AProblem1806().solution(s, arr).also { println(it) }
}

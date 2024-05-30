package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.min

class Problem1806 {
    fun solution(s: Int, arr: IntArray): Int {
        val numbers = intArrayOf(*arr, 0)
        var (left, right) = 0 to 0
        var total = 0
        var answer = Int.MAX_VALUE

        while (right < numbers.size) {
            if (total < s) total += numbers[right++]
            else {
                answer = min(answer, right - left)
                total -= numbers[left++]
            }
        }
        return if (answer == Int.MAX_VALUE) 0 else answer
    }
}

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }
    println(Problem1806().solution(s, arr))
}

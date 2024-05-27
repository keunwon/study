package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.min

class Problem2512 {
    fun solution(numbers: IntArray, target: Int): Int {
        numbers.sort()

        var left = 1
        var right = numbers.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = numbers.sumOf { n -> min(n, mid) }

            if (sum <= target) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() {
    val n = readln().toInt()
    val numbers = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }
    val target = readln().toInt()

    println(Problem2512().solution(numbers, target))
}

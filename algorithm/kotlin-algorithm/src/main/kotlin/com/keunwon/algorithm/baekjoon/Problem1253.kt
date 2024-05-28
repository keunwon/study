package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1253 {
    fun solution(arr: IntArray): Int {
        arr.sort()
        return arr.indices.count { check(arr, it) }
    }

    private fun check(arr: IntArray, index: Int): Boolean {
        val target = arr[index]
        var left = 0
        var right = arr.lastIndex

        while (left < right) {
            if (left == index) ++left
            else if (right == index) --right

            if (left >= right) return false

            val sum = arr[left] + arr[right]
            when {
                sum < target -> ++left
                sum > target -> --right
                else -> return true
            }
        }
        return false
    }
}

fun main() {
    val n = readln().toInt()
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem1253().solution(arr))
}

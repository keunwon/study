package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class AProblem2470 {
    fun solution(arr: IntArray): IntArray {
        arr.sort()

        var gap = Int.MAX_VALUE
        val answer = intArrayOf(0, 0)
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val tmp = abs(arr[left] + arr[right])

            if (tmp < gap) {
                gap = tmp
                answer[0] = arr[left]
                answer[1] = arr[right]
            }
            if (tmp < 0) left++ else right--
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    AProblem2470().solution(arr).also {
        println(it.joinToString(" "))
    }
}

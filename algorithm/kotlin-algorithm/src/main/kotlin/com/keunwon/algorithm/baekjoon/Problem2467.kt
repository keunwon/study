package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.abs

class Problem2467 {
    fun solution(arr: LongArray): LongArray {
        arr.sort()

        var left = 0
        var right = arr.lastIndex
        var min = Long.MAX_VALUE
        val answer = longArrayOf(arr[left], arr[right])

        while (left < right) {
            val sum = arr[left] + arr[right]
            val num = abs(sum)

            if (min >= num) {
                min = num
                answer[0] = arr[left]
                answer[1] = arr[right]
            }
            if (sum < 0) ++left else --right
        }
        return answer
    }
}

fun main() {
    val n = readln().toInt()
    val arr = run {
        val st = StringTokenizer(readln())
        LongArray(n) { st.nextToken().toLong() }
    }

    Problem2467().solution(arr).also { println(it.joinToString(" ")) }
}

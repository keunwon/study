package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem11501 {
    fun solution(arr: LongArray): Long {
        var answer = 0L
        var max = arr.last()

        for (i in arr.lastIndex downTo 0) {
            if (max > arr[i]) {
                answer += max - arr[i]
            } else if (max < arr[i]) {
                max = arr[i]
            }
        }
        return answer
    }
}

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()
        val st = StringTokenizer(readln())
        val arr = LongArray(n) { st.nextToken().toLong() }

        println(Problem11501().solution(arr))
    }
}

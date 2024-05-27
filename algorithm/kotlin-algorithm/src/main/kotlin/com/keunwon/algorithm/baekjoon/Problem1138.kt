package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1138 {
    fun solution(arr: IntArray): IntArray {
        val answer = mutableListOf<Int>()

        for (i in arr.lastIndex downTo 0) {
            val num = arr[i]
            answer.add(num, i + 1)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readln().toInt()
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    Problem1138().solution(arr).also { println(it.joinToString(" ")) }
}

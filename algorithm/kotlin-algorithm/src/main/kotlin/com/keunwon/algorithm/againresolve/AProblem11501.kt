package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem11501 {
    fun solution(arr: IntArray): Long {
        var max = 0L
        var answer = 0L

        for (i in arr.lastIndex downTo 0) {
            if (max < arr[i]) max = arr[i].toLong()
            else answer += max - arr[i]
        }
        return answer
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val st = StringTokenizer(readLine()!!)
        val arr = IntArray(n) { st.nextToken().toInt() }

        AProblem11501().solution(arr).also { println(it) }
    }
}

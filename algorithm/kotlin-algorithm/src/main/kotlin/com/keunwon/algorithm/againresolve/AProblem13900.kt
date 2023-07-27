package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem13900 {
    fun solution(arr: IntArray): Long {
        val prefixSum = LongArray(arr.size).apply {
            for (i in 1 until arr.size) this[i] = this[i - 1] + arr[i]
        }

        var answer = 0L
        for (i in arr.indices) {
            val n = prefixSum.last() - prefixSum[i]
            answer += arr[i] * n
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val st = StringTokenizer(readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }

    AProblem13900().solution(arr).also { println(it) }
}

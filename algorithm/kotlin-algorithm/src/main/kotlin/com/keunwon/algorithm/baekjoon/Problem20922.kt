package com.keunwon.algorithm.baekjoon

import java.util.*
import kotlin.math.max

class Problem20922 {
    fun solution(k: Int, arr: IntArray): Int {
        val dp = IntArray(200_001)
        var left = 0
        var right = 0
        var answer = 0

        while (right < arr.size) {
            if (dp[arr[right]] < k) {
                ++dp[arr[right]]
                ++right
                answer = max(answer, right - left)
            } else {
                --dp[arr[left]]
                ++left
            }
        }
        return answer
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem20922().solution(k, arr))
}

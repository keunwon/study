package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem21921 {
    fun solution(x: Int, arr: IntArray): String {
        var sum = (0 until x).sumOf { arr[it] }
        val answer = intArrayOf(sum, 1)

        for (i in x until arr.size) {
            sum -= arr[i - x]
            sum += arr[i]

            if (answer[0] == sum) {
                ++answer[1]
            } else if (answer[0] < sum) {
                answer[0] = sum
                answer[1] = 1
            }
        }

        if (answer[0] == 0) return "SAD"
        return answer.joinToString(System.lineSeparator())
    }
}

fun main() {
    val (n, x) = readln().split(" ").map { it.toInt() }
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem21921().solution(x, arr))
}

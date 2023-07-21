package com.keunwon.algorithm.baekjoon

/**
 * Title: 수 고르기
 * Level: 골드-5
 **/
class Problem2230 {
    fun solution(m: Int, arr: IntArray): Int {
        arr.sort()

        var answer = Int.MAX_VALUE
        var left = 0
        var right = 0

        while (right < arr.size) {
            val num = arr[right] - arr[left]

            when {
                m == num -> return m
                m < num -> {
                    left++
                    answer = answer.coerceAtMost(num)
                }
                m > num -> right++
            }
        }
        return answer
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem2230().solution(m, arr).also { println(it) }
}

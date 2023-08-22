package com.keunwon.algorithm.baekjoon

/**
 * Title: 블랙잭
 * Level: 브론즈-2
 **/
class Problem2798 {
    fun solution(m: Int, arr: IntArray): Int {
        var answer = 0

        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                for (k in j + 1 until arr.size) {
                    val sum = arr[i] + arr[j] + arr[k]
                    if (sum in answer + 1..m) answer = sum
                }
            }
        }
        return answer
    }
}

fun main() {
    val readIntArray = { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
    val (n, m) = readIntArray()
    val arr = readIntArray()

    Problem2798().solution(m, arr).also(::println)
}

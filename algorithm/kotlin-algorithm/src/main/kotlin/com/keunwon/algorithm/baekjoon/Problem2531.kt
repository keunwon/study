package com.keunwon.algorithm.baekjoon

/**
 * Title: 회전 초밥
 * Level: 실버-1
 **/
class Problem2531 {
    fun solution(d: Int, k: Int, c: Int, arr: IntArray): Int {
        val n = arr.size
        val eats = IntArray(d + 1)
        var count = 0

        for (i in 0 until k) {
            val num = arr[i]
            if (eats[num] == 0) count++
            eats[num]++
        }

        var max = count
        for (i in 0 until n) {
            if (max <= count) {
                max = if (eats[c] == 0) count + 1 else count
            }

            val startNum = arr[i]
            eats[startNum]--
            if (eats[startNum] == 0) count--

            val endNum = arr[(i + k) % n]
            if (eats[endNum] == 0) count++
            eats[endNum]++
        }
        return max
    }
}

fun main() {
    val (n, d, k, c) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }
    Problem2531().solution(d, k, c, arr).also { println(it) }
}

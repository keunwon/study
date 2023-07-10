package com.keunwon.algorithm.baekjoon

/**
 * Title: 어두운 굴다리
 * Level: 실버-4
 **/
class Problem17266 {
    fun solution(n: Int, arr: IntArray): Int {
        var left = 1
        var right = n

        while (left <= right) {
            val mid = (left + right) / 2

            if (check(n, mid, arr)) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    private fun check(n: Int, h: Int, arr: IntArray): Boolean {
        var preNum = 0
        for (num in arr) {
            if (num - h <= preNum) preNum = num + h
            else return false
        }
        return n - preNum <= 0
    }
}

fun main() {
    val (n, m) = IntArray(2) { readLine()!!.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem17266().solution(n, arr).also { println(it) }
}

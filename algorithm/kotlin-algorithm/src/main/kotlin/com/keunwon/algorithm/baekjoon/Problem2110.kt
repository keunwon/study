package com.keunwon.algorithm.baekjoon

/**
 * Title: 공유기 설치
 * Level: 골드-4
 **/
class Problem2110 {
    fun solution(c: Int, arr: IntArray): Int {
        arr.sort()

        var left = arr.first()
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2

            if (c <= getCount(mid, arr)) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun getCount(distance: Int, arr: IntArray): Int {
        var count = 1
        var pre = arr[0]

        for (i in 1 until arr.size) {
            if (distance <= arr[i] - pre) {
                pre = arr[i]
                count++
            }
        }
        return count
    }
}

fun main() {
    val (n, c) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem2110().solution(c, arr).also { println(it) }
}

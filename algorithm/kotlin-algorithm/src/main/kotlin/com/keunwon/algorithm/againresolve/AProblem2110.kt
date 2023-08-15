package com.keunwon.algorithm.againresolve

class AProblem2110 {
    fun solution(c: Int, arr: IntArray): Int {
        arr.sort()

        var left = arr.first()
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val count = getCount(mid, arr)

            if (c <= count) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun getCount(distance: Int, arr: IntArray): Int {
        var count = 1
        var pre = arr.first()

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

    AProblem2110().solution(c, arr).also(::println)
}

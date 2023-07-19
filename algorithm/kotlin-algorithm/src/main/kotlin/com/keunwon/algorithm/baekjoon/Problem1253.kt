package com.keunwon.algorithm.baekjoon

/**
 * Title: 좋다
 * Level: 골드-4
 **/
class Problem1253 {
    private lateinit var arr: IntArray

    fun solution(arr: IntArray): Int {
        this.arr = arr
        return arr.indices.count { check(it) }
    }

    private fun check(idx: Int): Boolean {
        val target = arr[idx]
        var left = 0
        var right = arr.lastIndex

        while (left < right) {
            if (left == idx) left++
            else if (right == idx) right--

            if (left >= right) break

            val sum = arr[left] + arr[right]
            when {
                target > sum -> left++
                target < sum -> right--
                else -> return true
            }
        }
        return false
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ")
        .map { it.toInt() }
        .toIntArray()
        .also { it.sort() }

    Problem1253().solution(arr).also { println(it) }
}

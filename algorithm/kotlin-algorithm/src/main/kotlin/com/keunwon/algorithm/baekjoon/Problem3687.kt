package com.keunwon.algorithm.baekjoon

import kotlin.math.min

/**
 * Title: 성냥개비
 * Level: 골드-2
 **/
class Problem3687 {
    fun solution(arr: IntArray): Array<String> {
        val minDp = LongArray(101) { Long.MAX_VALUE }.apply {
            set(2, 1)
            set(3, 7)
            set(4, 4)
            set(5, 2)
            set(6, 6)
            set(7, 8)
            set(8, 10)
        }
        val maxDp = Array(101) { "" }.apply {
            set(2, "1")
            set(3, "7")
        }
        val nums = intArrayOf(1, 7, 4, 2, 0, 8)

        for (i in 9..100) {
            for (j in 2..7) {
                val tmp = minDp[i - j] * 10 + nums[j - 2]
                minDp[i] = min(minDp[i], tmp)
            }
        }
        for (i in 4..100) {
            maxDp[i] = maxDp[i - 2] + "1"
        }
        return arr.map { n -> "${minDp[n]} ${maxDp[n]}" }.toTypedArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }
    Problem3687().solution(arr).forEach { println(it) }
}

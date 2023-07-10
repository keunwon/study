package com.keunwon.algorithm.baekjoon

import kotlin.math.abs

/**
 * Title: 두 용액
 * Level: 골드-5
 **/
class Problem2470 {
    fun solution(arr: IntArray): IntArray {
        arr.sort()

        var gap = Int.MAX_VALUE
        var (target1, target2) = 0 to 0
        var (left, right) = 0 to arr.lastIndex

        while (left < right) {
            val sum = arr[left] + arr[right]
            val absNum = abs(sum)

            if (absNum < gap) {
                gap = absNum
                target1 = arr[left]
                target2 = arr[right]
            }
            if (0 < sum) right-- else left++
        }
        return intArrayOf(target1, target2)
    }
}

fun main() {
    readLine()!!
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem2470().solution(arr).also { println(it.joinToString(" ")) }
}

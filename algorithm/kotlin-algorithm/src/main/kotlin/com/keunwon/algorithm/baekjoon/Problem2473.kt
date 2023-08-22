package com.keunwon.algorithm.baekjoon

import kotlin.math.absoluteValue

/**
 * Title: 세 용액
 * Level: 골드-3
 **/
class Problem2473 {
    fun solution(arr: LongArray): LongArray {
        arr.sort()

        val picks = LongArray(3)
        var gap = Long.MAX_VALUE

        for (i in arr.indices) {
            var left = i
            var mid = i + 1
            var right = arr.lastIndex

            while (mid < right) {
                val sum = arr[left] + arr[mid] + arr[right]

                if (gap > sum.absoluteValue) {
                    gap = sum.absoluteValue
                    picks[0] = arr[left]
                    picks[1] = arr[mid]
                    picks[2] = arr[right]
                }

                when {
                    sum == 0L -> break
                    sum < 0L -> mid++
                    sum > 0L -> right--
                }
            }
        }
        return picks.sortedArray()
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val arr = br.readLine().split(" ").map { it.toLong() }.toLongArray()

        Problem2473().solution(arr).forEach { bw.write("$it ") }
        bw.flush()
    }
}

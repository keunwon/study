package com.keunwon.algorithm.leetcode

import kotlin.math.ceil

class `875_Koko_Eating_Bananas` {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var left = 1
        var right = piles.max()

        while (left <= right) {
            val mid = (left + right) / 2
            val hour = piles.sumOf { ceil(it.toDouble() / mid) }.toInt()

            if (hour <= h) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }
}

fun main() {
    `875_Koko_Eating_Bananas`().minEatingSpeed(intArrayOf(3, 6, 7, 11), 8)
        .also { println(it) } // 4

    `875_Koko_Eating_Bananas`().minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 5)
        .also { println(it) } // 30

    `875_Koko_Eating_Bananas`().minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 6)
        .also { println(it) } // 23
}

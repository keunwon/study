package com.keunwon.algorithm.leetcode

import kotlin.math.max

class `1732_Find_the_Highest_Altitude` {
    fun largestAltitude(gain: IntArray): Int {
        val arr = IntArray(gain.size + 1)
        var max = 0

        for ((i, n) in gain.withIndex()) {
            arr[i + 1] = arr[i] + n
            max = max(max, arr[i + 1])
        }
        return max
    }
}

fun main() {
    `1732_Find_the_Highest_Altitude`().largestAltitude(intArrayOf(-5, 1, 5, 0, -7)).also { println(it) } // 1
    `1732_Find_the_Highest_Altitude`().largestAltitude(intArrayOf(-4, -3, -2, -1, 4, 3, 2)).also { println(it) } // 0
}

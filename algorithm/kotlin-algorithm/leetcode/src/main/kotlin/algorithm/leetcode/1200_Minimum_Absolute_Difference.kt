package algorithm.leetcode

import kotlin.math.abs

class `1200_Minimum_Absolute_Difference` {
    fun minimumAbsDifference(arr: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        var min = 1e9.toInt()

        arr.sort()

        for (i in 0 until arr.lastIndex) {
            val diff = abs(arr[i] - arr[i + 1])
            if (min > diff) {
                min = diff
                result.clear()
                result.add(listOf(arr[i], arr[i + 1]))
            } else if (min == diff) {
                result.add(listOf(arr[i], arr[i + 1]))
            }
        }
        return result
    }
}

fun main() {
    `1200_Minimum_Absolute_Difference`().minimumAbsDifference(intArrayOf())
}

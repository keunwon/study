package com.keunwon.algorithm.programmers

/**
 * Title: 징검다리
 * Level: 4
 **/
class Lessons43236 {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        rocks.sort()
        return binarySearch(distance, rocks, n)
    }

    private fun binarySearch(distance: Int, rocks: IntArray, n: Int): Int {
        var left = 1
        var right = distance
        while (left <= right) {
            val mid = (left + right) / 2

            if (brokeRocks(rocks, distance, mid) <= n) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun brokeRocks(rocks: IntArray, distance: Int, mid: Int): Int {
        var count = 0
        var prev = 0

        for (rock in rocks) {
            if (rock - prev < mid) ++count
            else prev = rock
        }
        if (distance - prev < mid) ++count
        return count
    }
}

package com.keunwon.algorithm.programmers

class Lesson43236 {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        rocks.sort()

        var left = 1
        var right = distance

        while (left <= right) {
            val mid = (left + right) / 2

            if (brokenRocks(rocks, distance, mid) <= n) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun brokenRocks(rocks: IntArray, distance: Int, min: Int): Int {
        var broken = 0
        var pre = 0

        for (rock in rocks) {
            if (rock - pre < min) ++broken else pre = rock
        }
        if (distance - pre < min) ++broken
        return broken
    }
}

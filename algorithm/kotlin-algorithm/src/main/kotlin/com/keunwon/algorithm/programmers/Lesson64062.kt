package com.keunwon.algorithm.programmers

class Lesson64062 {
    fun solution(stones: IntArray, k: Int): Int {
        var left = 0
        var right = 200_000_000

        while (left <= right) {
            val mid = (left + right) / 2

            if (check(stones, mid, k)) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun check(stones: IntArray, mid: Int, k: Int): Boolean {
        var pass = 0
        for (stone in stones) {
            if (stone < mid) {
                ++pass
                if (pass == k) return false
            } else {
                pass = 0
            }
        }
        return true
    }
}
package com.keunwon.algorithm.programmers

/**
 * Title: 징검다리 건너기
 * Level: 3
 **/
class Lessons64062 {
    fun solution(stones: IntArray, k: Int): Int {
        var left = 0
        var right = 200_000_000

        while (left <= right) {
            val mid = (left + right) / 2

            if (check(mid, stones, k)) left = mid + 1
            else right = mid - 1
        }
        return right
    }

    private fun check(mid: Int, stones: IntArray, k: Int): Boolean {
        var count = 0
        for (stone in stones) {
            if (stone < mid) {
                count++
                if (k <= count) return false
            } else count = 0
        }
        return true
    }
}

package com.keunwon.algorithm.againresolve

class ALessons64062 {
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
                ++count
                if (k <= count) return false
            } else count = 0
        }
        return true
    }
}

fun main() {
    ALessons64062().solution(
        intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1),
        3
    ).also(::println)
}

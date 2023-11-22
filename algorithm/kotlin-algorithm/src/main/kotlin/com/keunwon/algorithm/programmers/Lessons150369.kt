package com.keunwon.algorithm.programmers

class Lessons150369 {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer = 0L
        var d = 0
        var p = 0

        for (i in n - 1 downTo 0) {
            d += deliveries[i]
            p += pickups[i]

            while (d > 0 || p > 0) {
                answer += 2 * (i + 1)
                d -= cap
                p -= cap
            }
        }
        return answer
    }
}

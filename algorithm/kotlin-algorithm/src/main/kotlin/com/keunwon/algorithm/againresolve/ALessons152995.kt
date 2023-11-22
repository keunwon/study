package com.keunwon.algorithm.againresolve

class ALessons152995 {
    fun solution(scores: Array<IntArray>): Int {
        val whanho = scores[0]
        var rank = 1
        var max = 0

        scores.sortWith(compareBy({ -it[0] }, { it[1] }))

        for ((n1, n2) in scores) {
            if (n2 < max) {
                if (whanho[0] == n1 && whanho[1] == n2) return -1
            } else {
                max = n2
                if (whanho.sum() < n1 + n2) ++rank
            }
        }
        return rank
    }
}

fun main() {
    ALessons152995().solution(
        arrayOf(
            intArrayOf(2, 2),
            intArrayOf(1, 4),
            intArrayOf(3, 2),
            intArrayOf(3, 2),
            intArrayOf(2, 1)
        )
    ).also(::println)
}

package com.keunwon.algorithm.againresolve

class ALessons181188 {
    fun solution(targets: Array<IntArray>): Int {
        targets.sortWith(compareBy({ it[1] }, { it[0] }))

        var answer = 0
        var cur = -1
        for ((s, e) in targets) {
            if (s >= cur) {
                ++answer
                cur = e
            }
        }
        return answer
    }
}

fun main() {
    ALessons181188().solution(
        arrayOf(
            intArrayOf(4, 5),
            intArrayOf(4, 8),
            intArrayOf(10, 14),
            intArrayOf(11, 13),
            intArrayOf(5, 12),
            intArrayOf(3, 7),
            intArrayOf(1, 4)
        )
    ).also(::println)
}

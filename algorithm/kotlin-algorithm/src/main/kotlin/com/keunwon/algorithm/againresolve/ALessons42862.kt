package com.keunwon.algorithm.againresolve

class ALessons42862 {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val lostSet = lost.toSet() - reserve.toSet()
        val reserveSet = (reserve.toSet() - lost.toSet()).toMutableSet()
        var answer = n

        for (l in lostSet.sorted()) {
            when {
                l - 1 in reserveSet -> reserveSet.remove(l - 1)
                l + 1 in reserveSet -> reserveSet.remove(l + 1)
                else -> answer--
            }
        }
        return answer
    }
}

fun main() {
    val n = 5
    val lost = intArrayOf(2, 4)
    val reserve = intArrayOf(1, 3, 5)

    ALessons42862().solution(n, lost, reserve).also { println(it) }
}

package com.keunwon.algorithm.programmers

/**
 * Title: 체육복
 * Level: 1
 **/
class Lessons42862 {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = n
        val lostSet = lost.toSet() - reserve.toSet()
        val reserveSet = (reserve.toSet() - lost.toSet()).toMutableSet()

        for (i in lostSet.sorted()) {
            if (i - 1 in reserveSet) reserveSet.remove(i - 1)
            else if (i + 1 in reserveSet) reserveSet.remove(i + 1)
            else answer--
        }
        return answer
    }
}

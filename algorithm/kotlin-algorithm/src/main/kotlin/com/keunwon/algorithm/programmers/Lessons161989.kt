package com.keunwon.algorithm.programmers

/**
 * Title: 덧칠하기
 * Level: 1
 **/
class Lessons161989 {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var answer = 0
        var paint = 0

        for (i in section) {
            if (paint <= i) {
                paint = m + i
                answer++
            }
        }
        return answer
    }
}

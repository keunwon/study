package com.keunwon.algorithm.programmers

/**
 * Title: 요격 시스템
 * Level: 2
 **/
class Lessons181188 {
    fun solution(targets: Array<IntArray>): Int {
        targets.sortWith(compareBy({ it[1] }, { it[0] }))

        var answer = 0
        var cur = -1

        for ((start, end) in targets) {
            if (start >= cur) {
                answer++
                cur = end
            }
        }
        return answer
    }
}

package com.keunwon.algorithm.programmers

class Lesson181188 {
    fun solution(targets: Array<IntArray>): Int {
        targets.sortWith(compareBy({ it[1] }, { it[0] }))
        var prev = -1
        var answer = 0

        for ((s, e) in targets) {
            if (prev <= s) {
                ++answer
                prev = e
            }
        }
        return answer
    }
}
package com.keunwon.algorithm.programmers

class Lesson42889 {
    fun solution(N: Int, stages: IntArray): IntArray {
        var person = stages.size
        val stageMap = (1..N).associateWith { s -> stages.count { it == s } }
        return intArrayOf()
    }
}

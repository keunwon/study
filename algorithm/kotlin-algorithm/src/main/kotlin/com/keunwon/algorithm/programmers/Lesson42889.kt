package com.keunwon.algorithm.programmers

class Lesson42889 {
    fun solution(N: Int, stages: IntArray): IntArray {
        val peoples = IntArray(N + 2).apply { stages.forEach { ++this[it] } }
        var ret = stages.size
        val rates = Array(N) { it + 1 to 0.0 }

        for (i in 0 until N) {
            val rate = peoples[i + 1].toDouble() / ret

            rates[i] = rates[i].copy(second = rate)
            ret -= peoples[i + 1]

            if (ret == 0) break
        }

        return rates.sortedByDescending { it.second }.map { it.first }.toIntArray()
    }
}

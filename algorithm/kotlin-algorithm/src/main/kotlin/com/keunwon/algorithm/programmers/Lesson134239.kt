package com.keunwon.algorithm.programmers

class Lesson134239 {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val list = generateSequence(k) { if (it % 2 == 0) it / 2 else it * 3 + 1 }
            .takeWhile { it > 1 }
            .plus(1)
            .toList()
        val areas = mutableListOf<Double>()

        for (i in 0 until list.lastIndex) {
            val tmp = (list[i] + list[i + 1]) / 2.0
            areas.add(tmp)
        }
        return ranges.map { range ->
            val a = range[0]
            val b = areas.size + range[1]

            if (a > b) -1.0 else (a until b).sumOf { areas[it] }
        }.toDoubleArray()
    }
}

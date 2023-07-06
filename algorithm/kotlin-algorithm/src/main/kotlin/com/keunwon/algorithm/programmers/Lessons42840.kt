package com.keunwon.algorithm.programmers

/**
 * Title: 모의고사
 * Level: 1
 **/
class Lessons42840 {
    fun solution(answers: IntArray): IntArray {
        val numbers = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
            intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5),
        )
        val getCount = { arr: IntArray -> answers.indices.count { answers[it] == arr[it % arr.size] } }
        val points = IntArray(3) { getCount(numbers[it]) }
        return points.indices
            .filter { i -> points.maxOf { it } == points[i] }
            .map { it + 1 }
            .toIntArray()
    }
}

package com.keunwon.algorithm.programmers

class Lesson68644 {
    fun solution(numbers: IntArray): IntArray {
        val set = mutableSetOf<Int>()
        for (i in numbers.indices) {
            for (j in i + 1 until numbers.size) {
                set.add(numbers[i] + numbers[j])
            }
        }
        return set.toIntArray().apply { sort() }
    }
}

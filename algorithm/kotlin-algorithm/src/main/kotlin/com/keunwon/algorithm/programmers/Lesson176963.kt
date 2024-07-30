package com.keunwon.algorithm.programmers

class Lesson176963 {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val map = name.zip(yearning.toList()).toMap()
        val result = IntArray(photo.size)

        for ((i, ph) in photo.withIndex()) {
            val sum = ph.sumOf { map.getOrDefault(it, 0) }
            result[i] = sum
        }
        return result
    }
}

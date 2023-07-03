package com.keunwon.algorithm.programmers

/**
 * Title: 추억 점수
 * Level: 1
 **/
class Lessons176963 {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val points = mutableMapOf<String, Int>().apply {
            name.forEachIndexed { index, str -> put(str, yearning[index]) }
        }
        return IntArray(photo.size) { i ->
            photo[i].sumOf { points.getOrDefault(it, 0) }
        }
    }
}

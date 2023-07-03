package com.keunwon.algorithm.programmers

/**
 * Title: 나누어 떨어지는 숫자 배열
 * Level: 1
 **/
class Lessons12910 {
    fun solution(arr: IntArray, divisor: Int): IntArray {
        val list = arr.filter { it % divisor == 0 }.sorted()
        return if (list.isEmpty()) intArrayOf(-1) else list.toIntArray()
    }
}

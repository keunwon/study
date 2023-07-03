package com.keunwon.algorithm.programmers

/**
 * Title: 제일 작은 수 제거하기
 * Level: 1
 **/
class Lessons12935 {
    fun solution(arr: IntArray): IntArray {
        return if (arr.size == 1) intArrayOf(-1)
        else arr.toMutableList().apply { remove(arr.minOf { it }) }.toIntArray()
    }
}

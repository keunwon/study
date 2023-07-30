package com.keunwon.algorithm.programmers

import kotlin.math.max

/**
 * Title: n^2 배열 자르기
 * Level: 2
 **/
class Lessons87390 {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        return (left..right).map {
            max(it / n, it % n).toInt() + 1
        }.toIntArray()
    }
}

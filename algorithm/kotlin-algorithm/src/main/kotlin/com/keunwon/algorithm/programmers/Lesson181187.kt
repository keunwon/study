package com.keunwon.algorithm.programmers

import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * <p>
 * 이름: 두 원 사이의 정수 쌍
 * 난이도: Level-2
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181187">두 원 사이의 정수 쌍 (Level-2)</a>
 **/
class Lesson181187 {
    fun solution(r1: Int, r2: Int): Long {
        val min = r1.toDouble() * r1
        val max = r2.toDouble() * r2
        var count = 0L

        for (x in 1..r2) {
            val n = x.toDouble() * x
            val y1 = ceil(sqrt(min - n)).toLong()
            val y2 = sqrt(max - n).toLong()

            count += y2 - y1 + 1
        }
        return count * 4
    }
}
